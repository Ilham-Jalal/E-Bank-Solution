package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.UserService;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users =userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PutMapping("up/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user2) {
        User User = userService.getUserById(id);
            User.setUserName(user2.getUserName());
            User.setUserPrenom(user2.getUserPrenom());
            User.setUserEmail(user2.getUserEmail());
            User.setCompteBancaire(user2.getCompteBancaire());
            User updatedUser = userService.saveUser(User);
            return ResponseEntity.ok(updatedUser);
    }
}
