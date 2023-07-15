package com.example.usermanagementapp.controller;

import com.example.usermanagementapp.dto.UserDTO;
import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.exceptions.UserErrorResponse;
import com.example.usermanagementapp.exceptions.UserNotFoundException;
import com.example.usermanagementapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Endpoints")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @Operation(
            description = "Returns a json using pageable. Can also be used to sort and change sorting direction." +
                    "Default sort is by first name and ascending. Default page number is 0 and page size is 10."
    )
    @GetMapping("/users")
    public List<UserDTO> findAllUsers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "firstName", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection
    ) {

        return userService.findAllUsers(pageNo, pageSize, sortBy, sortDirection);
    }

    @Operation(
            description = "Returns a single user by its id. " +
                    "Throws UserNotFoundException if there is no user with the id."
    )
    @GetMapping("/users/{id}")
    // @ExceptionHandler(UserNotFoundException.class)
    public UserDTO findUserById(@PathVariable long id) {

        UserDTO userDTO = userService.findById(id);

        if (userDTO == null) {
            throw new UserNotFoundException("User Id not found " + id);
        }

        return userDTO;
    }

    @Operation(
            description = "Creates a user, accepts the user as json. All fields are required and cannot be null."
    )
    @PostMapping("/users")
    public User createUser(@RequestBody UserDTO userDTO) {

        UserDTO tempUserDTO = userDTO;
        //this is to prevent user update instead of user creation
        tempUserDTO.setId(0);

        return userService.save(tempUserDTO);
    }

    @Operation(
            description = "Updates a user in the database, accepts it as json"
    )
    @PutMapping("/users")
    public User updateUser(@RequestBody UserDTO userDTO) {

        return userService.save(userDTO);

    }

    @Operation(
            description = "Deletes the user with the provided id, returns confirmation as text," +
                    " throws UserNotFoundException if there is no user with the id provided"
    )
    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable long id) {

        UserDTO userDTO = userService.findById(id);

        if (userDTO == null) {
            throw new UserNotFoundException("User id not found: " + id);
        }

        userService.deleteById(id);

        return "Deleted user with id: " + id;
    }

    @Operation(
            description = "Searches for a user, returns all users if no parameter provided. " +
                    "Searches in both first and last name and will return even if it is a single letter."
    )
    @GetMapping("/users/search")
    public List<UserDTO> result(
            @RequestParam(value = "searchVariable", defaultValue = "", required = true) String searchVariable
    ) {

        List<UserDTO> resultList = userService.findUsersBySearch(searchVariable);
        return resultList;
    }


    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc) {

        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        userErrorResponse.setMessage(exc.getMessage());
        userErrorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }
}
