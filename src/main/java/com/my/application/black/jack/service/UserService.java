package com.my.application.black.jack.service;


import com.my.application.black.jack.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}
