package com.swe266group12.bankapp.controller;

import com.swe266group12.bankapp.model.BankUser;
import com.swe266group12.bankapp.model.Deposit;
import com.swe266group12.bankapp.model.User;
import com.swe266group12.bankapp.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model, @SessionAttribute("user") User user) {
        BankUser bankUser = userRepository.findByUsername(user.getUsername()).get(0);
        user.setBalance(UserController.longToString(bankUser.getBalance()));

        model.addAttribute("deposit", new Deposit("", ""));
        model.addAttribute("user", user);
        model.addAttribute("profile-link", "11");
        model.addAttribute("profile", getImageString(user.getUsername()));
        return "home";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam(value = "file") MultipartFile file, @SessionAttribute("user") User user)
            throws IOException {

        Path path = Paths.get("upload/" + user.getUsername());
        Files.createDirectories(path);
        File storedFile = new File("upload/" + user.getUsername() + "/" + file.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(storedFile)) {
            outputStream.write(file.getBytes());
        }

        return "redirect:/home";
    }

    @GetMapping(value = "/profile/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] serveFile(@SessionAttribute("user") User user,
                                          @PathVariable("filename") String filename) throws IOException {
        InputStream in = new FileInputStream("upload/" + user.getUsername() + "/" +filename);
        return IOUtils.toByteArray(in);
    }

    public static String getImageString(String username) {
        try {
            File dir = new File("upload/" + username);
            File[] files = dir.listFiles();
            if (files.length == 0) return "";

            InputStream image = new FileInputStream(files[0]);
            return Base64.getEncoder().encodeToString(image.readAllBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public static String getImagePath(String username) {
        try {
            File dir = new File("upload/" + username);
            File[] files = dir.listFiles();

            InputStream image = new FileInputStream(files[0]);
            return files[0].getName();
        } catch (Exception e) {
        }
        return "";
    }
}
