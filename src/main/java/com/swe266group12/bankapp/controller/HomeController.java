package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.model.Deposit;
import com.swe266group12.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model, @SessionAttribute BankUser user) {
        model.addAttribute("deposit", new Deposit(0L, 0L));
        model.addAttribute("user", user);
        return "home";
    }
}
