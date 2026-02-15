package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void 상품_등록_테스트() {
        // 1. 상품 객체 생성
        Product product = Product.builder()
                .productName("테스트 상품명") // 상품명
                .pDescription("테스트 상품에 대한 설명") //  상품설명
                .price(new BigDecimal("150000")) // 가격, 금액은 BigDecimal 사용 권장
                .stock(10) // 재고수량
                .pStatus("SELL") // 상품 판매 상태
                .build();

        // 2. DB 저장
        Product savedProduct = productRepository.save(product);

        // 3. 결과 확인
        System.out.println("저장된 상품 정보: " + savedProduct);
    }
}