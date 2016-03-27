package com.my.application.black.jack.server.service;


import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.exception.UserAlreadyExistsException;
import com.my.application.black.jack.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {

        LOGGER.debug("Creating {}", user);
        User existing;
        if (user.getEmail() != null && (existing = repository.findByEmail(user.getEmail())) != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", existing.getId()));
        }

        return repository.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

    @Override
    public User findByEmail(String username) {
        return repository.findByEmail(username);
    }

}
