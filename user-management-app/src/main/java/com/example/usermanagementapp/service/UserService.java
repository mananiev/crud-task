package com.example.usermanagementapp.service;

import com.example.usermanagementapp.dto.UserDTO;
import com.example.usermanagementapp.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    UserDTO findById(long id);

    User save(UserDTO userDTO);

    void deleteById(long id);
}
