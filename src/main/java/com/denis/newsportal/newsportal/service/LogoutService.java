package com.denis.newsportal.newsportal.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LogoutService {

    public String logoutUser (final HttpServletRequest request,final HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("nonymousUser")) return "no authorized";
            else {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return "you logged out";
        }
    }
}
