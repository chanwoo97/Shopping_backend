package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.domain.*;
import com.shopping.shopping_backend_server.dto.OrderRequestDTO;
import com.shopping.shopping_backend_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Long order(OrderRequestDTO dto) { // 파라미터를 낱개가 아닌 DTO 상자로 받음

        // 1. 엔티티 조회 (ERD 기반: memberId가 아닌 userId 사용)
        Member member = memberRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        // 2. 비즈니스 로직: 재고 감소 (Usecase 기반)
        product.removeStock(dto.getCount());

        // 3. 주문(Order) 엔티티 생성 및 저장
        Order order = Order.builder()
                .member(member)
                .address(member.getAddress()) // 회원의 기본 배송지 사용
                .orderStatus("ORDER")
                .build();

        orderRepository.save(order);

        // 4. 주문 상세(OrderItem) 엔티티 생성 및 저장
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .orderPrice(product.getPrice().intValue())
                .count(dto.getCount())
                .build();

        orderItemRepository.save(orderItem);

        // 5. 결과 반환 (주문 번호)
        return order.getOrderId();
    }
}