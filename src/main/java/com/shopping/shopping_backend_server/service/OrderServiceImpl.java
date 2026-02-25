package com.shopping.shopping_backend_server.service;

import com.shopping.shopping_backend_server.domain.*;
import com.shopping.shopping_backend_server.dto.OrderRequestDTO;
import com.shopping.shopping_backend_server.dto.OrderResponseDTO; // 응답 DTO 추가
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
    public OrderResponseDTO order(OrderRequestDTO dto) { // 반환 타입을 DTO로 변경 [순서 3]

        // 1. 엔티티 조회 (DB에서 원본 VO를 가져오는 과정) [순서 1]
        Member member = memberRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        // 2. 재고 감소 로직
        product.removeStock(dto.getCount());

        // 3. 주문(Order) 엔티티 생성 및 저장
        Order order = Order.builder()
                .member(member)
                .address(member.getAddress())
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

        // 5. [순서 2: VO -> DTO 변환] 별표(*) 친 핵심 로직!
        // DB에 저장된 무거운 엔티티(VO) 정보를 가벼운 도시락(DTO)에 옮겨 담습니다.
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .productName(product.getProductName())
                .totalPrice(orderItem.getOrderPrice() * orderItem.getCount())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}