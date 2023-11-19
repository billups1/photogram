package com.gg.photogram.service;

import com.gg.photogram.domain.image.Image;
import com.gg.photogram.domain.likes.Likes;
import com.gg.photogram.domain.likes.LikesRepository;
import com.gg.photogram.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void like(long imageId, long principalId) {
        User user = new User();
        user.setId(principalId);
        Image image = new Image();
        image.setId(imageId);
        Likes like = Likes.builder()
                .user(user)
                .image(image)
                .build();
        likesRepository.save(like);
    }

    @Transactional
    public void unLike(long imageId, long principalId) {
        likesRepository.mUnLike(imageId, principalId);
    }
}
