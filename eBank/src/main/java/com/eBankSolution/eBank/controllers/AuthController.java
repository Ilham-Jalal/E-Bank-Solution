package com.eBankSolution.eBank.controllers;//package com.eBankSolution.eBank.controllers;
//
//import com.eBankSolution.eBank.Services.UserService;
//import com.eBankSolution.eBank.config.JwtHelper;
//import com.eBankSolution.eBank.controllers.dto.LoginRequest;
//import com.eBankSolution.eBank.controllers.dto.SignupRequest;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
//public class AuthController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//
//    private final UserService userService;
//
//    public AuthController(UserService userService, JwtHelper jwtHelper) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest requestDto) {
//        userService.signUp(requestDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
////        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
////        String token= JwtHelper.generateToken(loginRequest.getUsername());
////        return ResponseEntity.ok(token);
////    }
//@PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
//        }
//    }


import com.eBankSolution.eBank.Services.UserService;
import com.eBankSolution.eBank.config.JwtHelper;
import com.eBankSolution.eBank.controllers.dto.LoginRequest;
import com.eBankSolution.eBank.controllers.dto.SignupRequest;
import com.eBankSolution.eBank.controllers.dto.login;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;



    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody login loginRequest) {
        System.out.println("/////////////////");
        System.out.println("////////////"+loginRequest.password()+"//////////"+loginRequest.username());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        if (authentication.isAuthenticated()) {
            String token = JwtHelper.generateToken(loginRequest.username());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }
}
