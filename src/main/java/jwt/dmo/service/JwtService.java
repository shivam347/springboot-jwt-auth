package jwt.dmo.service;


import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret; // key used to sign the jwt

    @Value("${jwt.expiration}")
    private long expiryDate; // token validity duration in milliseconds

    /*
     * Method to generate the signing key
     * Convert the string to key object
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes); // generates SecretKey for HS256
    }

    /* Method to generate the token */
    public String generateToken(UserDetails user) {

        return Jwts.builder()
                .subject(user.getUsername()) // store the username in the token
                .issuedAt(new Date()) // issued date when token created
                .expiration(new Date(System.currentTimeMillis() + expiryDate)) // expiration time
                .signWith(getSignKey()) // signs token with secret key using HMAC SHA 256
                .compact(); // creates final jwt token

    }

    /* Method to extract the claims from the token */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build() // create the parser
                .parseSignedClaims(token)  // verifies token and decodes header and payload
                .getPayload(); // return the payload object 
        return claimsResolver.apply(claims);
    }


    /* Method to extract the username from the token*/
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /* Method to extract the password from the token */
    public Date extractExpiration(String token){

        return extractClaim(token, Claims::getExpiration);
    }

    /* Method to check the token expired or not */
    private boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());  
    }


    /* Method to validate the token checks username is same or not and also check the expiry of token */
    public boolean validateToken(String token, UserDetails user){

        return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

}
