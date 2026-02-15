package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }