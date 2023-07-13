package com.example.usermanagementapp.controller;

import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "You are home";
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {

        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable long id) {

        User user = userService.findById(id);

        if (user == null) {
            throw  new RuntimeException("User Id not found " + id);
        }

        return user;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        //this is to prevent user update instead of user creation
        user.setId(0);

        return userService.save(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        return userService.save(user);
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable long id) {
        User user = userService.findById(id);

        if (user == null) {
            throw new RuntimeException("User id not found: " + id);
        }

        userService.deleteById(id);

        return "Deleted user with id: " + id;
    }
}
