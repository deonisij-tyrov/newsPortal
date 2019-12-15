package com.denis.newsportal.newsportal.controller;

import com.denis.newsportal.newsportal.dto.NewsDto;
import com.denis.newsportal.newsportal.service.NewsService;
import com.denis.newsportal.newsportal.service.UserService;
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
    public ResponseEntity createUser(@RequestParam final String userName, @RequestParam final String role) {
        String updateRole = userService.updateRole(userName, role);
        return new ResponseEntity(updateRole, HttpStatus.OK);
    }


    @PostMapping("/createNews")
    public String createNews(@RequestBody final NewsDto newsDto) {
        boolean admin = SecurityContextHolder
                .getContext()
                .getAuthentication().getCredentials().toString().contains("ADMIN");
        return newsService.createNews(newsDto);
    }

    @DeleteMapping("/deleteNews")
    public String deleteNews(@RequestBody final String newsName) {
        return newsService.deleteNews(newsName);
    }

    @PostMapping("/updateNews")
    public String updateNews(@RequestBody final NewsDto newsDto) {
        return newsService.updateNews(newsDto);
    }
}
