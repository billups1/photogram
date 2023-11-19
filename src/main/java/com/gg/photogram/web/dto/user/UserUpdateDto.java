package com.gg.photogram.web.dto.user;

import com.gg.photogram.domain.user.User;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

}
