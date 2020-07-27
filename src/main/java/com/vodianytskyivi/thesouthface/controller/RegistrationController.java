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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String registration(
            @RequestParam(defaultValue = "") String usernameError,
            @RequestParam(defaultValue = "") String passwordError,
            @RequestParam(defaultValue = "") String message
            ) {
        return "registration";
    }

    @PostMapping("/registration")
    public RedirectView addUser(
            User user,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        boolean isPasswordEmpty = isEmpty(user.getPassword());
        boolean isUsernameEmpty = isEmpty(user.getUsername());

        if (isPasswordEmpty || isUsernameEmpty) {
            if (isUsernameEmpty) {
                redirectAttributes.addAttribute("usernameError", "Username can't be blank");
            }
            if (isPasswordEmpty) {
                redirectAttributes.addAttribute("passwordError", "Password can't be blank");
            }
            return new RedirectView("/registration", true);
        }

        if (!userService.addUser(user)) {
            redirectAttributes.addAttribute("message", "User exists!");
            return new RedirectView("/registration", true);
        }

        authenticateUserAndSetSession(user, request);
        redirectAttributes.addAttribute("warningMessage", "Please, activate your account.");
        return new RedirectView("/", true);
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    @GetMapping("/activate/{code}")
    public RedirectView activate(
            Model model,
            @PathVariable String code,
            RedirectAttributes redirectAttributes
    ) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            redirectAttributes.addAttribute("successMessage", "User successfully activated!");
        } else {
            redirectAttributes.addAttribute("dangerMessage", "Activation code is not found!");
        }
        return new RedirectView("/", true);
    }
}
