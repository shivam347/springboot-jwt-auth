package jwt.dmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwt.dmo.entity.UserEntity;
import jwt.dmo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;


    public void saveUser(String username, String password){
         UserEntity user = UserEntity.builder()
         .name(username)
         .password(encoder.encode(password))  // hash password
         .build();

    userRepository.save(user);
    }
    
}
