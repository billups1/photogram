package com.gg.photogram.web.api;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.service.LikesService;
import com.gg.photogram.web.dto.ex.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/api/image/{imageId}/like")
    public ResponseEntity like(@PathVariable long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.like(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/{imageId}/like")
    public ResponseEntity unLike(@PathVariable long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLike(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소 성공", null), HttpStatus.OK);
    }

}
