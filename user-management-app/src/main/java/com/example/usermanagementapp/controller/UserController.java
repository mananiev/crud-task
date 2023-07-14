package com.example.usermanagementapp.controller;

import com.example.usermanagementapp.dto.UserDTO;
import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {

        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO findUserById(@PathVariable long id) {

        UserDTO userDTO = userService.findById(id);

        if (userDTO == null) {
            throw  new RuntimeException("User Id not found " + id);
        }

        return userDTO;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserDTO userDTO) {

        UserDTO tempUserDTO = userDTO;
        //this is to prevent user update instead of user creation
        tempUserDTO.setId(0);

        return userService.save(tempUserDTO);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody UserDTO userDTO) {

        return userService.save(userDTO);

    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable long id) {

        UserDTO userDTO = userService.findById(id);

        if (userDTO == null) {
            throw new RuntimeException("User id not found: " + id);
        }

        userService.deleteById(id);

        return "Deleted user with id: " + id;
    }
}
