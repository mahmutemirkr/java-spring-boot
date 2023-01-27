package com.jvtpe.security.service;

import com.jvtpe.domain.Role;
import com.jvtpe.domain.User;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).orElseThrow(
                ()-> new ResourceNotFoundException("User not found username : " + username));

        if(user != null){

            return new org.
                    springframework.
                    security.
                    core.
                    userdetails.
                    User(user.getUserName(),
                         user.getPassword(),
                       buildGrantedAutorities(user.getRoles()));

        } else {

            throw new UsernameNotFoundException("User not found username : " + username);

        }

    }

    private static List<SimpleGrantedAuthority> buildGrantedAutorities(final Set<Role> roles){


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {

            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

        }

        return authorities;

    }





}
