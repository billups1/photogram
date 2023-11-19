package com.gg.photogram.web.api;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.user.User;
import com.gg.photogram.service.SubscribeService;
import com.gg.photogram.service.UserService;
import com.gg.photogram.web.dto.ex.CMRespDto;
import com.gg.photogram.web.dto.subscribe.SubscribeDto;
import com.gg.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @PutMapping("/user/update")
    public ResponseEntity userUpdate(@Validated UserUpdateDto userUpdateDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        userService.userUpdate(principalDetails.getUser().getId(), userUpdateDto);

        return new ResponseEntity(new CMRespDto<>(1,"회원수정 성공", null), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity subscribeList(@PathVariable long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDtos = subscribeService.subscribeList(pageUserId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 리스트 불러오기 성공", subscribeDtos), HttpStatus.OK);
    }

    @PostMapping("/api/user/{principalId}/profileImage")
    public ResponseEntity profileImage(@PathVariable long principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = userService.profileImage(principalId, profileImageFile);
        principalDetails.setUser(user);
        return new ResponseEntity<>(new CMRespDto<>(1,"사진업로드 성공", null), HttpStatus.CREATED);
    }

}
