package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model) {
        BankUser user = userRepository.findByUsername("kelly").get(0);
        model.addAttribute("user", user);
        model.addAttribute("amount", 10);
        return "home";
    }
}
