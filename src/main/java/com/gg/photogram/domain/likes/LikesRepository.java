package com.gg.photogram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "DELETE FROM likes WHERE fromUserId = :principalId AND toImageId = :imageId", nativeQuery = true)
    void mUnLike(long imageId, long principalId);

    @Query(value = "select count(*) from likes where fromUserId = :principalId and toImageId = :imageId", nativeQuery = true)
    int likeStatus(long principalId, long imageId);
}
