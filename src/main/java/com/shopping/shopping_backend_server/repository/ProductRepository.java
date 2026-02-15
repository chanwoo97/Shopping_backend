package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<관리할 엔티티, ID의 타입>
public interface ProductRepository extends JpaRepository<Product, Long> {
}