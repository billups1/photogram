package com.gg.photogram.domain.likes;

import com.gg.photogram.domain.image.Image;
import com.gg.photogram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        uniqueConstraints =
        @UniqueConstraint(
                name = "likes_uk",
                columnNames = {"fromUserId", "toImageId"}
        )
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "toImageId")
    private Image image;

    private LocalDateTime createTime;

    @PrePersist
    public void createTime() {
        this.createTime = LocalDateTime.now();
    }

}
