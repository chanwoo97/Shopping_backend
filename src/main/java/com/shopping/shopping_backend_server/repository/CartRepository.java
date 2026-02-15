package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> { }