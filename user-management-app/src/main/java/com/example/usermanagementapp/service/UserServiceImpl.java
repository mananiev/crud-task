package com.example.usermanagementapp.service;

import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        //Have to use Optional as we are not sure the user with the id exists
        Optional<User> optionalUser = userRepository.findById(id);

        User user = null;

        //Checking if the optional is present or not
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("Did not find the user with id: " + id);
        }

        return user;
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {

        userRepository.deleteById(id);
    }
}
