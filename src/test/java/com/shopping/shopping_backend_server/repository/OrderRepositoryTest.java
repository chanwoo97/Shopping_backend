package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // 실제 서버를 띄우는 것과 유사하게 스프링의 모든 설정(Bean)을 다 불러옴
@Transactional // 테스트 완료 후 데이터를 롤백(삭제)해서 DB를 깨끗하게 유지
public class OrderRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired ProductRepository productRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("주문 생성 및 재고 감소 테스트")
    public void 상품_주문_테스트() {
        // 1. 회원 등록
        Member member = Member.builder()
                .username("buyer01").name("구매자").build();
        memberRepository.save(member);

        // 2. 상품 등록 (재고 10개)
        Product product = Product.builder()
                .productName("기능성 운동화")
                .price(new BigDecimal("100000"))
                .stock(10)
                .build();
        productRepository.save(product);

        // 3. 주문 생성
        Order order = Order.builder()
                .member(member)
                .address("서울시 강남구")
                .orderStatus("ORDER")
                .build();
        orderRepository.save(order);

        // 4. 주문 상세 생성 및 재고 감소 로직 호출
        int orderCount = 3;
        product.removeStock(orderCount); // Product 엔티티에 만든 메서드 사용!

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .orderPrice(product.getPrice().intValue())
                .count(orderCount)
                .build();
        orderItemRepository.save(orderItem);

        // 5. 검증
        Product savedProduct = productRepository.findById(product.getProductId()).orElseThrow();
        System.out.println("주문 후 남은 재고: " + savedProduct.getStock());

        // 남은 재고가 7개여야 함
        assertThat(savedProduct.getStock()).isEqualTo(7);
    }
}