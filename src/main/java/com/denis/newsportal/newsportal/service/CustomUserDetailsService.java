package com.denis.newsportal.newsportal.service;

import com.denis.newsportal.newsportal.entity.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = userService.getUser(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        if (!user.isActive()) {
            throw new DisabledException(login);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), user.isActive(), true, true,
                true, getAuthorities(user.getRole().toString()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        final List<GrantedAuthority> authorities = Arrays.asList(simpleGrantedAuthority);
        return authorities;
    }
}
