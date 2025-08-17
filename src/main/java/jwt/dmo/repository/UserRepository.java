package jwt.dmo.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.dmo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long > {
    
   Optional< UserEntity> findByName(String username);
}
