package com.reactive.Employee.service;

import com.reactive.Employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.reactive.Employee.model.User> optionalUser = userRepository.findByUsername(username);

        // Handling optional properly
        com.reactive.Employee.model.User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));

        // Creating a Spring Security user
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())  
                .build();



    }
}
