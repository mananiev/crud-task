package com.example.usermanagementapp.service;

import com.example.usermanagementapp.dto.UserDTO;
import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserDTO> userDTOList = userList.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());


        return userDTOList;
    }

    @Override
    public UserDTO findById(long id) {
        //Have to use Optional as we are not sure the user with the id exists
        Optional<User> optionalUser = userRepository.findById(id);

        UserDTO userDTO = null;

        //Checking if the optional is present or not
        if (optionalUser.isPresent()) {
            userDTO = modelMapper.map(optionalUser.get(), UserDTO.class);
        } else {
            throw new RuntimeException("Did not find the user with id: " + id);
        }


        return userDTO;
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {

        userRepository.deleteById(id);
    }
}
