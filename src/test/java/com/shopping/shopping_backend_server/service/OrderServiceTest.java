package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.domain.Member;
import com.shopping.shopping_backend_server.domain.Product;
import com.shopping.shopping_backend_server.dto.OrderRequestDTO; // DTO 임포트 추가
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

    @Autowired OrderService orderService;
    @Autowired MemberRepository memberRepository;
    @Autowired ProductRepository productRepository;

    @Test
    @DisplayName("DTO를 통한 주문 및 재고 감소 테스트")
    public void 서비스_주문_테스트() {
        // 1. 가짜 데이터 준비 (DB 설계 및 ERD 기반)
        Member member = Member.builder()
                .username("dtoUser")
                .name("DTO사용자")
                .address("서울시 강남구")
                .build();
        memberRepository.save(member);

        Product product = Product.builder()
                .productName("DTO 테스트 운동화")
                .price(new BigDecimal("100000"))
                .stock(10)
                .build();
        productRepository.save(product);

        // 2. 배달 상자(DTO) 만들고 데이터 담기
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setUserId(member.getUserId());     // ERD의 userId 사용
        orderRequestDTO.setProductId(product.getProductId());
        orderRequestDTO.setCount(3); // 3개 주문

        // 3. 서비스 호출 (상자째로 전달!)
        Long orderId = orderService.order(orderRequestDTO);

        // 4. 검증 (Usecase: 재고가 정상적으로 줄었는가?)
        Product savedProduct = productRepository.findById(product.getProductId()).orElseThrow();

        System.out.println("주문 번호: " + orderId);
        System.out.println("남은 재고: " + savedProduct.getStock());

        assertThat(savedProduct.getStock()).isEqualTo(7); // 10 - 3 = 7
        assertThat(orderId).isNotNull();
    }
}