package com.learning.controller;

import com.learning.utils.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/view")
    String getUserByName(@RequestParam String name, Model model) {
        model.addAttribute("user", usersRepository.findByName(name));
        return "users_view";
    }
}
