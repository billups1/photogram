package com.gg.photogram.web.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class SignupDto {

    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
//    @Email
    private String email;
    private String phone;
    private String gender;

}
