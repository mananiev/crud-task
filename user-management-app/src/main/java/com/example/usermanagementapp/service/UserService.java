package com.example.usermanagementapp.service;

import com.example.usermanagementapp.dto.UserDTO;
import com.example.usermanagementapp.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers(int pageNo, int pageSize);

    UserDTO findById(long id);

    User save(UserDTO userDTO);

    void deleteById(long id);

    //Page<UserDTO> findAllUsers();
}
