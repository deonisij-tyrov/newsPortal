package com.denis.newsportal.newsportal.controller;

import com.denis.newsportal.newsportal.service.LogoutService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public String logoutUser (final HttpServletRequest request, final HttpServletResponse response) {
        return logoutService.logoutUser(request, response);
    }
}
