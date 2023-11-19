package com.gg.photogram.web.api;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.image.Image;
import com.gg.photogram.service.ImageService;
import com.gg.photogram.web.dto.ex.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imageService;

    @GetMapping("/api/image/story")
    public ResponseEntity story(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 3) Pageable pageable) {
        List<Image> images = imageService.story(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"스토리 불러오기 완료", images), HttpStatus.OK);
    }

}
