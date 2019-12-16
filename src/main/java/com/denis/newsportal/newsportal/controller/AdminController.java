package com.denis.newsportal.newsportal.controller;

import com.denis.newsportal.newsportal.dto.NewsDto;
import com.denis.newsportal.newsportal.service.NewsService;
import com.denis.newsportal.newsportal.service.UserService;
import com.sun.javafx.binding.StringFormatter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/admin")
@Api(value = "Admin Resource REST Endpoint", description = "Admin panel")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @PostMapping("/updaterole")
    public ResponseEntity createUser(@RequestParam String userName, @RequestParam String role) {
        String updateRole = userService.updateRole(userName, role);
        return new ResponseEntity(updateRole, HttpStatus.OK);
    }


    @PostMapping("/createNews")
    public ResponseEntity createNews(@RequestBody NewsDto newsDto) {
        boolean admin = SecurityContextHolder
                .getContext()
                .getAuthentication().getCredentials().toString().contains("ADMIN");
        return new ResponseEntity(newsService.createNews(newsDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteNews")
    public ResponseEntity deleteNews(@RequestBody String newsName) {
        return new ResponseEntity(newsService.deleteNews(newsName), HttpStatus.OK);
    }

    @PostMapping("/updateNews")
    public ResponseEntity updateNews(@RequestBody NewsDto newsDto) {
        return new ResponseEntity(newsService.updateNews(newsDto), HttpStatus.OK);
    }

    @GetMapping("/enableUser")
    public ResponseEntity enableUser(@RequestParam String userName) {
        userService.setUserEnabledStatus(userName, true);
        return new ResponseEntity(StringFormatter.format("User %s enabled", userName), HttpStatus.OK);
    }

    @GetMapping("/disableUser")
    public ResponseEntity disableUser(@RequestParam String userName) {
        userService.setUserEnabledStatus(userName, false);
        return new ResponseEntity(StringFormatter.format("User %s disabled", userName), HttpStatus.OK);
    }
}
