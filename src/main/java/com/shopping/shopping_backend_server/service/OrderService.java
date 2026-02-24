package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.dto.OrderRequestDTO;

public interface OrderService {

    Long order(OrderRequestDTO dto);
}