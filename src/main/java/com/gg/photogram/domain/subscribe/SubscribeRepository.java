package com.gg.photogram.domain.subscribe;

import com.gg.photogram.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying
    @Query(value = "INSERT INTO subscribe (fromUserId, toUserId, createTime) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(long fromUserId, long toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(long fromUserId, long toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    int mSubscribeState(long fromUserId, long toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(long pageUserId);
}
