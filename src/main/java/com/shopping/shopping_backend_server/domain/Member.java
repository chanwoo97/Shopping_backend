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
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username; // 로그인용 아이디
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private String role; // ROLE_USER, ROLE_ADMIN 등
}