package jwt.dmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jwt.dmo.model.AuthRequest;
import jwt.dmo.model.AuthResponse;
import jwt.dmo.model.MessageResponse;
import jwt.dmo.service.CustomUserDetailService;
import jwt.dmo.service.JwtService;
import jwt.dmo.service.UserService;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailService userDetails;

    @GetMapping("hello")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @PostMapping("register")
    public MessageResponse register(@RequestBody AuthRequest request) {
        userService.saveUser(request.getUsername(), request.getPassword());
        return new MessageResponse("User registered Successfully");
    }

    @PostMapping("login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
            )
        );
        UserDetails user = userDetails.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
