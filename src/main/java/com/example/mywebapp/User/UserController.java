package com.example.mywebapp.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<user>listUsers=service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new user());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(user users, RedirectAttributes ra){
        service.save(users);
        ra.addFlashAttribute("message","The user has been saved successfully");

        return "redirect:/users";

    }
}
