package com.swe266group12.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String welcome(Model model) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
            Statement stmt = conn.createStatement();
            stmt.execute("create schema IF NOT EXISTS testDB;");
            System.out.println("created db");
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        model.addAttribute("msg", "Welcome!");
        return "welcome";
    }
}
