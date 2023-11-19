package com.gg.photogram.web;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.image.Image;
import com.gg.photogram.service.ImageService;
import com.gg.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/upload")
    public String imageUploadForm() {
        return "image/upload";
    }

    @GetMapping("/image/popular")
    public String imagePopular(Model model) {
        List<Image> images = imageService.imagePopular();
        model.addAttribute("images", images);
        return "image/popular";
    }

    @PostMapping("/image/upload")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        imageService.imageUpload(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

}
