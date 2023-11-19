package com.gg.photogram.domain.subscribe;

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
                        name = "subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"}
                )
)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User fromUser;

    private LocalDateTime createTime;
    @PrePersist
    public void createTime() {
        this.createTime = LocalDateTime.now();
    }
}
