package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.model.Deposit;
import com.swe266group12.bankapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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
        if (!this.userRepository.findByUsername(user.getUsername()).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return new RedirectView("/register");
        }
        userRepository.save(user);
        return new RedirectView("/register");
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new BankUser());
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute("user") BankUser user, RedirectAttributes redirectAttributes, HttpSession session) {
        List<BankUser> result = this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (result.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Login failed!");
            return new RedirectView("/login");
        }
        session.setAttribute("user", result.get(0));
        redirectAttributes.addFlashAttribute("user", user);
        return new RedirectView("/home");
    }

    @PostMapping("/deposit")
    public RedirectView deposit(@ModelAttribute("deposit") Deposit deposit, RedirectAttributes redirectAttributes, @SessionAttribute("user") BankUser user) {
        Long balance = user.getBalance() + (deposit.getDeposit() != null ? deposit.getDeposit() : - deposit.getWithdraw());

        if (balance < 0) {
            redirectAttributes.addFlashAttribute("error", "Invalid withdrawal!");
        } else {
            user.setBalance(balance);
            userRepository.save(user);
        }
        return new RedirectView("/home");
    }

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./db", "root","secure_password");
    }
}
