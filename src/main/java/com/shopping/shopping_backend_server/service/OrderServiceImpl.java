package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.domain.*;
import com.shopping.shopping_backend_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService { // OrderService 설계도를 따른다는 뜻

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override // 설계도에 있는 기능을 실제로 구현함
    public Long order(Long memberId, Long productId, int count) {

        // 1. 엔티티 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        // 2. 재고 감소
        product.removeStock(count);

        // 3. 주문 생성
        Order order = Order.builder()
                .member(member)
                .address(member.getAddress())
                .orderStatus("ORDER")
                .build();
        orderRepository.save(order);

        // 4. 주문 상세 생성
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .orderPrice(product.getPrice().intValue())
                .count(count)
                .build();
        orderItemRepository.save(orderItem);

        return order.getOrderId();
    }
}