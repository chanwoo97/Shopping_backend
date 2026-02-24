package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.domain.Member;
import com.shopping.shopping_backend_server.domain.Product;
import com.shopping.shopping_backend_server.repository.MemberRepository;
import com.shopping.shopping_backend_server.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired OrderService orderService; // 인터페이스를 주입받습니다.
    @Autowired MemberRepository memberRepository;
    @Autowired ProductRepository productRepository;

    @Test
    @DisplayName("서비스를 통한 주문 및 재고 감소 테스트")
    public void 서비스_주문_테스트() {
        // 1. 가짜 회원과 상품 준비 (기존과 동일)
        Member member = Member.builder()
                .username("serviceUser").name("서비스사용자").build();
        memberRepository.save(member);

        Product product = Product.builder()
                .productName("서비스 테스트 운동화")
                .price(new BigDecimal("50000"))
                .stock(10)
                .build();
        productRepository.save(product);

        // 2. 서비스 호출 (우리가 만든 order 메서드 실행!)
        int orderCount = 2;
        Long orderId = orderService.order(member.getUserId(), product.getProductId(), orderCount);

        // 3. 검증
        Product savedProduct = productRepository.findById(product.getProductId()).orElseThrow();

        // 10개 중 2개 주문했으니 8개가 남아야 함
        System.out.println("주문 후 남은 재고: " + savedProduct.getStock());
        assertThat(savedProduct.getStock()).isEqualTo(8);
        assertThat(orderId).isNotNull(); // 주문 번호가 생성되었는지 확인
    }
}