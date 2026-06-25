package com.belajar.demo_app.service;

import com.belajar.demo_app.model.User;

public interface UserService {
    User registerUser(User user);
    boolean existsByUsername(String username);
}
