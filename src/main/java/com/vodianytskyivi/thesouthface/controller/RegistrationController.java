package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.Role;
import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public RegistrationController(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, HttpServletRequest request) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        boolean isPasswordEmpty = user.getPassword() == null || user.getPassword().isEmpty();
        boolean isUsernameEmpty = user.getUsername() == null || user.getUsername().isEmpty();

        if (isPasswordEmpty && isUsernameEmpty) {
            model.put("message", "Username and password cant' be blank.");
            return "registration";
        } else if (isUsernameEmpty) {
            model.put("message", "Username can't be blank");
            return "registration";
        } else if (isPasswordEmpty) {
            model.put("message", "Password can't be blank");
            return "registration";
        }

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        authenticateUserAndSetSession(user, request);
        return "redirect:/main";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
