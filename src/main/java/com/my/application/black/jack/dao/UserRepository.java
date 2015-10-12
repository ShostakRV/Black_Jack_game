package com.my.application.black.jack.dao;


import com.my.application.black.jack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;


public interface UserRepository extends JpaRepository<User, Long> {
}
