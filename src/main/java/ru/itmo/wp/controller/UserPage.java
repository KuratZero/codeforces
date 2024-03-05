package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{idStr}")
    public String userData(@PathVariable String idStr, Model model) {
        long id;
        try {
            id = Long.parseLong(idStr);
            model.addAttribute("foundUser", userService.findById(id));
        } catch (NumberFormatException ignored) {
        }
        return "UserPage";
    }

    @GetMapping("/user")
    public String userDataInvalid() {
        return "UserPage";
    }
}
