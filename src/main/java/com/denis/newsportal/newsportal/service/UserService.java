package com.denis.newsportal.newsportal.service;

import com.denis.newsportal.newsportal.converter.UserConverter;
import com.denis.newsportal.newsportal.dto.UserDto;
import com.denis.newsportal.newsportal.entity.User;
import com.denis.newsportal.newsportal.enumeration.UserRole;
import com.denis.newsportal.newsportal.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserConverter userConverter;

    public UserService(final UserRepository userRepository, final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void registerUser(final UserDto userDto) {
        User user = userConverter.convertToDbo(userDto);
        user.setRole(UserRole.USER);
        user.setActive(true);
        userRepository.save(user);
    }

    public User getUser(String name) {
        return userRepository.findByLogin(name);
    }

    public Boolean isAdmin(final String login) {
        return userRepository.findByLogin(login).getRole().equals(UserRole.ADMIN);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    public String updateRole(String userName, String roleString) {
        UserRole userRole = null;
        for (UserRole role : UserRole.values()) {
            if (role.name().equals(roleString)) {
                userRole = role;
            }
        }
        if (userRole != null) {
            User user = userRepository.findByLogin(userName);
            if (user != null) {
                user.setRole(userRole);
                userRepository.save(user);
                return "Updated";
            } else {
                return "User not found";
            }
        } else {
            return "Wrong role";
        }

    }
}
