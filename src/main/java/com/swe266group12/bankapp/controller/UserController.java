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

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // register handler
    @PostMapping("/register")
    public RedirectView register(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
                                 HttpSession session) {
        // START BAD CODE (CWE-501)
        session.setAttribute("user", user);
        // END BAD CODE

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
        return new RedirectView("/home");
    }

    // login page
    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "target", required = false) String target,
                            HttpSession session) {
        if (session.getAttribute("user") != null) return "redirect:/home";

        if (target == null) target = "/home";
        session.setAttribute("target", target);

        model.addAttribute("user", new User());
        return "login";
    }

    // login handler
    @PostMapping("/login")
    public RedirectView login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
                              HttpSession session) {
        List<BankUser> result = this.userRepository.findByUsername(user.getUsername());
        if (result.isEmpty() || !encoder.matches(user.getPassword(), result.get(0).getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Login failed!");
            return new RedirectView("/login");
        }

        session.setAttribute("user", user);
        // START BAD CODE (CWE-601)
        return new RedirectView((String) session.getAttribute("target"));
        // END BAD CODE
    }

    // logout handler
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    // deposit and withdraw handler
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

    // convert between String format used by frontend and Long variable used by backend
    public static Long stringToLong(String s) {
        return Math.round(Double.valueOf(s) * 100);
    }

    // convert between String format used by frontend and Long variable used by backend
    public static String longToString(Long l) {
        return String.valueOf(l / 100.0);
    }

    // helper for number format validation
    private static boolean isValidNumber(String s) {
        return s.matches("(0|[1-9][0-9]*)\\.([0-9]{2})");
    }
}
