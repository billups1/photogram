package com.gg.photogram.service;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.comment.Comment;
import com.gg.photogram.domain.comment.CommentRepository;
import com.gg.photogram.domain.image.Image;
import com.gg.photogram.domain.user.User;
import com.gg.photogram.domain.user.UserRepository;
import com.gg.photogram.handler.ex.CustomApiException;
import com.gg.photogram.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment commentWrite(CommentDto commentDto, PrincipalDetails principalDetails) {
        Image image = new Image();
        image.setId(commentDto.getImageId());
        User user = userRepository.findById(principalDetails.getUser().getId()).orElseThrow(() -> {
            throw new CustomApiException("없는 유저입니다.");
        });

//        User user = principalDetails.getUser(); //->안됨: 객체는 트랜잭션에서 가져와야 하나봄

        Comment comment = Comment.builder()
                .user(user)
                .content(commentDto.getContent())
                .image(image)
                .build();

        commentRepository.save(comment);

        return comment;

    }

    @Transactional
    public void commentDelete(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
