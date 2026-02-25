package com.shopping.shopping_backend_server.controller;

import com.shopping.shopping_backend_server.dto.OrderRequestDTO;
import com.shopping.shopping_backend_server.dto.OrderResponseDTO;
import com.shopping.shopping_backend_server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문하기 API [구조도의 '컨트롤러 DTO 받기' 단계]
     * @RequestBody: 웹브라우저가 보낸 JSON 데이터를 OrderRequestDTO 객체로 자동 변환합니다.
     * 반환 타입: OrderResponseDTO [구조도의 '순서 3' 단계]
     */
    @PostMapping
    public OrderResponseDTO order(@RequestBody OrderRequestDTO orderRequestDTO) {

        // 서비스(주방장)에게 주문을 시키고, 결과가 담긴 DTO 상자를 받습니다.
        OrderResponseDTO response = orderService.order(orderRequestDTO);

        // 최종적으로 손님(웹브라우저)에게 결과 DTO를 전달합니다.
        return response;
    }
}