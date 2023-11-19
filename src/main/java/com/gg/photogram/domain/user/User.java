package com.gg.photogram.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gg.photogram.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, unique = true) // 확인필요 중복가입됨
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;
    private String role;
    private String userImageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"comments"})
    private List<Image> images;


    private LocalDateTime createTime;

    @PrePersist
    public void createTime() {
        this.createTime = LocalDateTime.now();
    }

}
