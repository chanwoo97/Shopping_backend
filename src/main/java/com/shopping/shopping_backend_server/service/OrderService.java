package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.dto.OrderRequestDTO;
import com.shopping.shopping_backend_server.dto.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO order(OrderRequestDTO dto);
}