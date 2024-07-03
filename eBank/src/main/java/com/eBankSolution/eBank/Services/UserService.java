package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.UserRepository;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
