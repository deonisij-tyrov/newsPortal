package com.denis.newsportal.newsportal.controller;

import com.denis.newsportal.newsportal.dto.UserDto;
import com.denis.newsportal.newsportal.service.UserService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST Endpoint", description = "Shows the user info")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody final UserDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity("Successfully registered", HttpStatus.OK);
    }

    @GetMapping("/getusers")
    public ResponseEntity getAllUsers() {
        final List<UserDto> userDtoList = userService.getUsers();
        if (userDtoList != null) {
            return new ResponseEntity(userDtoList, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}