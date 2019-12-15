package com.denis.newsportal.newsportal.converter;

import com.denis.newsportal.newsportal.dto.UserDto;
import com.denis.newsportal.newsportal.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoEntityConverter<UserDto, User> {

    @Override
    public UserDto convertToDto(final User entity) {
        final UserDto userDto = new UserDto();
        userDto.setLogin(entity.getLogin());
        userDto.setRole(entity.getRole().toString());
        return userDto;
    }

    @Override
    public User convertToDbo(final UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(getEncoder().encode(userDto.getPassword()));
        return user;
    }

    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
