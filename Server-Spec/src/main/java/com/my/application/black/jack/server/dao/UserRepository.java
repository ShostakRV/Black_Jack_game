package com.my.application.black.jack.server.dao;


import com.my.application.black.jack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
