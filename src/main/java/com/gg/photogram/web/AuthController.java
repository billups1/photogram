package com.gg.photogram.web;

import com.gg.photogram.service.AuthService;
import com.gg.photogram.service.UserService;
import com.gg.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Validated SignupDto signupDto, BindingResult bindingResult) {
        System.out.println("signup");
        authService.join(signupDto);
        return "redirect:/auth/signin";
    }
}
