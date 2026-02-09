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
@Table(name = "orders") //
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;

    private int totalPrice;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String orderStatus;

    // 어떤 회원이 주문했는가? (Member와 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
}