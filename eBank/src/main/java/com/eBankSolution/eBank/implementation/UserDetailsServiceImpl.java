package com.eBankSolution.eBank.implementation;

import com.eBankSolution.eBank.Repository.UserRepository;
import com.eBankSolution.eBank.controllers.dto.SignupRequest;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        System.out.println(user.getUsername()+"////"+user.getPassword());
        return (UserDetails) User.builder().username(user.getUsername()).password(user.getPassword()).build();
    }





}