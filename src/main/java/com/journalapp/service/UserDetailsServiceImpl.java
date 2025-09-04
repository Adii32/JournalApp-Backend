package com.journalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.journalapp.entity.User;
import com.journalapp.repo.UserRepo;

@Service   // 🔹 tells Spring to register this as a bean
public class UserDetailsServiceImpl implements UserDetailsService {  // 🔹 must implement interface

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);

        if (user != null) {
            // Build Spring Security's UserDetails object
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword()) // already encoded in DB
                    .roles(user.getRoles().split(",")) // convert List<String> → String[]
                    .build();
        }

        throw new UsernameNotFoundException("No user found with username: " + username);
    }
}
