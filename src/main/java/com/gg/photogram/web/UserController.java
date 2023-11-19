package com.gg.photogram.web;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.user.User;
import com.gg.photogram.service.UserService;
import com.gg.photogram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        UserProfileDto userProfileDto = userService.userProfile(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", userProfileDto);
        return "/user/profile";
    }

    @GetMapping("/user/update")
    public String updateForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "/user/update";
    }

}
