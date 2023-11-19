package com.gg.photogram.web.dto.user;

import com.gg.photogram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private User user;
    private int imageCount;
    private int subscribeCount;
    private boolean pageOwnerState;
    private boolean subscribeState;

    //이미지, 게시글수, 구독정보

}
