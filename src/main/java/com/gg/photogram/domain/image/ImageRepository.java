package com.gg.photogram.domain.image;

import com.gg.photogram.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "SELECT i.* FROM image i INNER JOIN subscribe s ON i.userId = s.toUserId WHERE s.fromUserId = :principalId ORDER BY id DESC", nativeQuery = true)
    List<Image> mStory(long principalId, Pageable pageable);

    @Query(value = "SELECT i.* FROM image i INNER JOIN (SELECT toImageId, COUNT(toImageId) imageCount FROM likes GROUP BY toImageId) l ON i.id = l.toImageId ORDER BY l.imageCount DESC", nativeQuery = true)
    List<Image> imagePopular();
}
