package com.gg.photogram.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gg.photogram.domain.comment.Comment;
import com.gg.photogram.domain.likes.Likes;
import com.gg.photogram.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String caption;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"images"})
    private User user;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"image"})
    @OrderBy("id DESC")
    private List<Comment> comments;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"image"})
    private List<Likes> likes;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;

    private LocalDateTime createTime;

    @PrePersist
    public void createTime() {
        this.createTime = LocalDateTime.now();
    }

}
