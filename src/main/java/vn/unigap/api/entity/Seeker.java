package vn.unigap.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "seeker")
public class Seeker {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private Integer province;

    @Column(name = "identification")
    private String identification;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private String status;

    @Column(name = "token")
    private String token;

    @Column(name = "logined_at")
    private String loginedAt;

    @Column(name = "logined_ip")
    private String loginedIp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
