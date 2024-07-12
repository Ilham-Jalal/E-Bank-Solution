package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.UserRepository;
import com.eBankSolution.eBank.config.JwtHelper;
import com.eBankSolution.eBank.controllers.dto.LoginRequest;
import com.eBankSolution.eBank.controllers.dto.SignupRequest;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Service pour la gestion des utilisateurs.
 */

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Recherche un utilisateur par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à rechercher.
     * @return L'utilisateur trouvé.
     * @throws java.util.NoSuchElementException Si aucun utilisateur avec cet identifiant n'est trouvé.
     */
    public User findUserById(int id) {
        return userRepository.findById((long) id).get();
    }

    @Transactional
    public void signUp(SignupRequest signupRequest) {
        String hashedPassword = passwordEncoder.encode(signupRequest.password());
        userRepository.save(User.builder().username(signupRequest.username()).password(hashedPassword).email(signupRequest.email()).build());
    }

}