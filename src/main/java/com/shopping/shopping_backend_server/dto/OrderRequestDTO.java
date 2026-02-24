package com.shopping.shopping_backend_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDTO {
    private Long userId;    // 주문자 번호
    private Long productId; // 상품 번호
    private int count;      // 수량
}