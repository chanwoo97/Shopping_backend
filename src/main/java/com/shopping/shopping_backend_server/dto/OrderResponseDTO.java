package com.shopping.shopping_backend_server.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private String productName;
    private int totalPrice;
    private String orderStatus;
}