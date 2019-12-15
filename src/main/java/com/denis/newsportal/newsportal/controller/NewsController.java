package com.denis.newsportal.newsportal.controller;

import com.denis.newsportal.newsportal.dto.NewsDto;
import com.denis.newsportal.newsportal.dto.UserDto;
import com.denis.newsportal.newsportal.service.NewsService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/news")
@Api(value = "News Resource REST Endpoint", description = "Shows the news info")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/getNews")
    public ResponseEntity getAllUsers(@RequestParam int page, @RequestParam int pageSize) {
        final List<NewsDto> userDtoList = newsService.getNews(page, pageSize);
        if (userDtoList != null) {
            return new ResponseEntity(userDtoList, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
