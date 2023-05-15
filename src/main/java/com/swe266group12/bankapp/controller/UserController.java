package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new BankUser());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute("user") BankUser user, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/register", true);
        userRepository.save(user);
        return redirectView;
    }

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:db", "sa","");
    }
}
