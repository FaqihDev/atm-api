package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.exception.UserNotActivatedException;
import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("username is not found"));

        if (!user.getIsActive()) {
            try {
                throw new UserNotActivatedException("User is not activated yet","Please verify your account!");
            } catch (UserNotActivatedException e) {
                throw new RuntimeException(e);
            }
        }
        return new User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }
}