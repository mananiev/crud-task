package com.example.usermanagementapp.service;

import com.example.usermanagementapp.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findById(long id);

    User save(User user);

    void deleteById(long id);
}
