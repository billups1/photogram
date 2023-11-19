package com.gg.photogram.web.api;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.service.SubscribeService;
import com.gg.photogram.web.dto.ex.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity subscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독 성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity unSubscribe(@PathVariable long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독취소 성공", null), HttpStatus.OK);
    }



}
