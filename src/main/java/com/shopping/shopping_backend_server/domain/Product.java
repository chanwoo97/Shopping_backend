package com.shopping.shopping_backend_server.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // ERD 기준: 상품 번호표

    @Column(nullable = false)
    private String productName; // 상품명

    @Column(nullable = false)
    private BigDecimal price; // 가격

    private int stock; // 재고 수량

    @Column(columnDefinition = "TEXT")
    private String pDescription; // 상품 설명
    private String pStatus; // 판매 상태


    // 재고 관리 시스템 (주문 시 재고를 줄이는 기능)
    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + this.stock);
        }
        this.stock = restStock;
    }

    // 입고/반품 시 재고 늘리는 기능
    public void addStock(int quantity) {
        this.stock += quantity;
    }
}