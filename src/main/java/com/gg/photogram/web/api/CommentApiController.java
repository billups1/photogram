package com.gg.photogram.web.api;

import com.gg.photogram.config.auth.PrincipalDetails;
import com.gg.photogram.domain.comment.Comment;
import com.gg.photogram.service.CommentService;
import com.gg.photogram.web.dto.comment.CommentDto;
import com.gg.photogram.web.dto.ex.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/image/comment")
    public ResponseEntity commentWrite(CommentDto commentDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Comment comment = commentService.commentWrite(commentDto, principalDetails);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글달기 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/comment/{commentId}")
    public ResponseEntity commentDelete(@PathVariable long commentId) {
        commentService.commentDelete(commentId);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제 성공", null), HttpStatus.OK);
    }

}
