package com.shopping.shopping_backend_server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class) // 시간 감시자 등록
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
    private String role; // ROLE_USER, ROLE_ADMIN 등

    @CreatedDate // 생성 시점 자동 기록
    @Column(updatable = false, columnDefinition = "DATETIME(0)") // 수정 시에는 시간이 안 바뀌게 고정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

}