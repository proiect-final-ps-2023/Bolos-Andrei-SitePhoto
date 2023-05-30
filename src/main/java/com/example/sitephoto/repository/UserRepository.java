package com.example.sitephoto.repository;

import com.example.sitephoto.DTO.UserDTO;
import com.example.sitephoto.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findFirstById(Long id);
    User findFirstByName(String name);
    User findFirstByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByNameAndPassword(String name, String password);
}
