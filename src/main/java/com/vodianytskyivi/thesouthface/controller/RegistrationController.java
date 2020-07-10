package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public RegistrationController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
            Model model,
            HttpServletRequest request
    ) {
        boolean isPasswordEmpty = isEmpty(user.getPassword());
        boolean isUsernameEmpty = isEmpty(user.getUsername());

        if (isPasswordEmpty || isUsernameEmpty) {
            if (isUsernameEmpty) {
                model.addAttribute("usernameError", "Username can't be blank");
            }
            if (isPasswordEmpty) {
                model.addAttribute("passwordError", "Password can't be blank");
            }
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }


        authenticateUserAndSetSession(user, request);
        model.addAttribute("warningMessage", "Please, activate your account.");
        return "greeting";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("successMessage", "User successfully activated!");
        } else {
            model.addAttribute("dangerMessage", "Activation code is not found!");
        }

        return "greeting";
    }
}
