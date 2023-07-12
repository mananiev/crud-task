package com.example.usermanagementapp.controller;

import com.example.usermanagementapp.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
}
