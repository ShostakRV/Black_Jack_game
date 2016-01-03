package com.my.application.black.jack.dao;

import com.my.application.black.jack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created: Shostak Roman
 * Date: 03.01.2016.
 */
//@Repository
public abstract class UserRepositoryImpl {//extends SimpleJpaRepository<User, Long> implements UserRepository {
//    @Autowired
//    public UserRepositoryImpl( EntityManager entityManager) {
//        super(User.class, entityManager);
//    }
}
