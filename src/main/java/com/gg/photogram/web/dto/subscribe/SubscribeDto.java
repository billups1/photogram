package com.gg.photogram.web.dto.subscribe;

import com.gg.photogram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeDto {

    private BigInteger userId;
    private String username;
    private String userImageUrl;
    private Integer equalUserState;
    private Integer subscribeState;

}
