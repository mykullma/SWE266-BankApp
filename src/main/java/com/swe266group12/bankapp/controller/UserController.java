package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.model.Deposit;
import com.swe266group12.bankapp.model.User;
import com.swe266group12.bankapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
                                 HttpSession session) {
        if (!user.getUsername().matches("[_\\-\\.0-9a-z]{1,127}") ||
                !user.getPassword().matches("[_\\-.0-9a-z]{1,127}") ||
                !isValidNumber(user.getBalance())) {
            redirectAttributes.addFlashAttribute("error", "invalid_input");
            return new RedirectView("/register");
        }
        if (!this.userRepository.findByUsername(user.getUsername()).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return new RedirectView("/register");
        }

        userRepository.save(new BankUser(user.getUsername(), encoder.encode(user.getPassword()), stringToLong(user.getBalance())));
        session.setAttribute("user", user);
        return new RedirectView("/home");
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, HttpSession session) {
        List<BankUser> result = this.userRepository.findByUsername(user.getUsername());
        if (result.isEmpty() || !encoder.matches(user.getPassword(), result.get(0).getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Login failed!");
            return new RedirectView("/login");
        }

        user.setBalance(longToString(result.get(0).getBalance()));
        session.setAttribute("user", user);
        return new RedirectView("/home");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/welcome";
    }

    @PostMapping("/deposit")
    public RedirectView deposit(@ModelAttribute("deposit") Deposit deposit, RedirectAttributes redirectAttributes, @SessionAttribute("user") User user) {
        Long balance = stringToLong(user.getBalance());

        if (deposit.getDeposit() != null) {
            if (isValidNumber(deposit.getDeposit())) {
                balance += stringToLong(deposit.getDeposit());
            } else {
                redirectAttributes.addFlashAttribute("error", "invalid_input");
            }
        } else if (deposit.getWithdraw() != null) {
            if (isValidNumber(deposit.getWithdraw())) {
                balance -= stringToLong(deposit.getWithdraw());
            } else {
                redirectAttributes.addFlashAttribute("error", "invalid_input");
            }
        }

        if (balance < 0) {
            redirectAttributes.addFlashAttribute("error", "Invalid withdrawal!");
        } else {
            user.setBalance(longToString(balance));
            BankUser bankUser = userRepository.findByUsername(user.getUsername()).get(0);
            bankUser.setBalance(balance);
            userRepository.save(bankUser);
        }
        return new RedirectView("/home");
    }

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./db", "root","secure_password");
    }

    public static Long stringToLong(String s) {
        return Math.round(Double.valueOf(s) * 100);
    }

    public static String longToString(Long l) {
        return String.valueOf(l / 100.0);
    }

    private boolean isValidNumber(String s) {
        return s.matches("(0|[1-9][0-9]*)\\.([0-9]{2})");
    }
}
