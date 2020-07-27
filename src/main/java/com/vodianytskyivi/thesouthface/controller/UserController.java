package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.Role;
import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userID") User user
    ) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "") String warningMessage
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("warningMessage", warningMessage);
        return "profile";
    }

    @PostMapping("profile")
    public RedirectView updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {
        boolean isEmailChanged = userService.isEmailDifferent(user, email);
        userService.updateUser(user, email, password);
        RedirectView redirectView = new RedirectView("/user/profile", true);
        if (isEmailChanged) {
            redirectAttributes.addAttribute("warningMessage", "Please, activate your account.");
        }
        return redirectView;
    }
}
