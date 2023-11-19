package com.gg.photogram.service;

import com.gg.photogram.domain.image.Image;
import com.gg.photogram.domain.subscribe.SubscribeRepository;
import com.gg.photogram.domain.user.User;
import com.gg.photogram.domain.user.UserRepository;
import com.gg.photogram.handler.ex.CustomApiException;
import com.gg.photogram.handler.ex.CustomException;
import com.gg.photogram.handler.ex.CustomValidationException;
import com.gg.photogram.web.dto.user.UserProfileDto;
import com.gg.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${path.file}")
    private String uploadFolder;

    @Transactional
    public void userUpdate(long principalId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(principalId).orElseThrow(() -> {
            throw new CustomValidationException("없는 회원입니다.");
        });

        user.setName(userUpdateDto.getName());
        user.setBio(userUpdateDto.getBio());
        user.setGender(userUpdateDto.getGender());
        user.setPhone(userUpdateDto.getPhone());
        String encPassword = bCryptPasswordEncoder.encode(userUpdateDto.getPassword());
        user.setPassword(encPassword);
        user.setWebsite(userUpdateDto.getWebsite());

    }

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(long pageUserId, long principalId) {
        User user = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("없는 회원입니다.");
        });

        UserProfileDto userProfileDto = new UserProfileDto();

        List<Image> images = user.getImages();
        for (Image image : images) {
            image.setLikeCount(image.getLikes().size());
        }
        userProfileDto.setUser(user);
        userProfileDto.setImageCount(user.getImages().size());
        userProfileDto.setPageOwnerState(pageUserId == principalId);
        userProfileDto.setSubscribeState(subscribeRepository.mSubscribeState(principalId, pageUserId) == 1);
        userProfileDto.setSubscribeCount(subscribeRepository.mSubscribeCount(pageUserId));



        return userProfileDto;
    }

    @Transactional
    public User profileImage(long principalId, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + file.getOriginalFilename();
        Path path = Paths.get(uploadFolder + imageFileName);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userRepository.findById(principalId).orElseThrow(() -> {
            throw new CustomApiException("없는 유저입니다.");
        });
        user.setUserImageUrl(imageFileName);
        return user;
    }
}
