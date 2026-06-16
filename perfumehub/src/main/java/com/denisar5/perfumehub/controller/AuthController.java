package com.denisar5.perfumehub.controller;

import com.denisar5.perfumehub.model.dto.UserLoginDto;
import com.denisar5.perfumehub.model.dto.UserRegisterDto;
import com.denisar5.perfumehub.model.entity.UserEntity;
import com.denisar5.perfumehub.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterDto")) {
            model.addAttribute("userRegisterDto", new UserRegisterDto());
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userRegisterDto") UserRegisterDto userRegisterDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(userRegisterDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginDto")) {
            model.addAttribute("userLoginDto", new UserLoginDto());
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("userLoginDto") UserLoginDto userLoginDto,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        UserEntity user = userService.login(userLoginDto);

        session.setAttribute("user_id", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("user_role", user.getRole());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}