package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }