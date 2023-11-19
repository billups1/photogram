package com.gg.photogram.service;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.image.Image;
import com.gg.photogram.domain.image.ImageRepository;
import com.gg.photogram.domain.likes.LikesRepository;
import com.gg.photogram.domain.user.User;
import com.gg.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final LikesRepository likesRepository;

    @Value("${path.file}")
    private String uploadFolder;

    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID();
        String imageUrl = uuid + imageUploadDto.getFile().getOriginalFilename();

        Path path = Paths.get(uploadFolder + imageUrl);

        try {
            Files.write(path, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = Image.builder()
                .imageUrl(imageUrl)
                .caption(imageUploadDto.getCaption())
                .user(principalDetails.getUser())
                .build();

        imageRepository.save(image);
    }

    @Transactional(readOnly = true)
    public List<Image> story(long principalId, Pageable pageable) {
        List<Image> images = imageRepository.mStory(principalId, pageable);

        for (Image image : images) {
            int likeStatus = likesRepository.likeStatus(principalId, image.getId());
            if (likeStatus == 1) {
                image.setLikeState(true);
            }
            image.setLikeCount(image.getLikes().size());
        }

        return images;
    }

    @Transactional(readOnly = true)
    public List<Image> imagePopular() {
        return imageRepository.imagePopular();
    }
}
