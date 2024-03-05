package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserDisableCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model, HttpSession session) {
        if(checkIsBlocked(session)) {
            setMessage(session, "You are disabled.");
            return "redirect:/";
        }
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all/disable")
    public String disableUser(@ModelAttribute("disableCredentials")UserDisableCredentials disableCredentials, HttpSession session) {
        if(checkIsBlocked(session)) {
            setMessage(session, "You are disabled.");
            return "redirect:/";
        }
        User user = userService.findById(disableCredentials.getId());
        if (user == null) {
            setMessage(session, "User not found");
        } else {
            user.setDisabled("disable".equals(disableCredentials.getStatus()));
        }
        userService.update(user);
        return "redirect:/users/all";
    }
}
