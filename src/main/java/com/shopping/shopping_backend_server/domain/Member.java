package com.shopping.shopping_backend_server.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberId; // 로그인용 아이디
    private String password;
    private String userName;
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN 등
    private LocalDateTime createdAt;
}