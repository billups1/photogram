package com.gg.photogram.service;

import com.gg.photogram.domain.user.User;
import com.gg.photogram.domain.user.UserRepository;
import com.gg.photogram.handler.ex.CustomApiException;
import com.gg.photogram.web.dto.auth.SignupDto;
import com.gg.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(SignupDto signupDto) {
        String encPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());

        User user = User.builder()
                .username(signupDto.getUsername())
                .name(signupDto.getName())
                .password(encPassword)
                .email(signupDto.getEmail())
                .role("General")
                .build();

        userRepository.save(user);
    }

}
