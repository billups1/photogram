package com.gg.photogram.service;

import com.gg.photogram.domain.subscribe.SubscribeRepository;
import com.gg.photogram.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional
    public void subscribe(long fromUserId, long toUserId) {
        subscribeRepository.mSubscribe(fromUserId, toUserId);

    }

    @Transactional
    public void unSubscribe(long fromUserId, long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);

    }

    public List<SubscribeDto> subscribeList(long pageUserId, long principalId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.userImageUrl, ");
        sb.append("(SELECT 1 WHERE s.toUserId = ?), ");
        sb.append("(SELECT 1 from subscribe WHERE fromUserId = ? AND toUserId=s.toUserId) ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ? ");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }
}
