package com.shopping.shopping_backend_server.service;

public interface OrderService {
    /**
     * 주문하기
     * @param memberId 회원 아이디
     * @param productId 상품 아이디
     * @param count 주문 수량
     * @return 생성된 주문의 ID
     */
    Long order(Long memberId, Long productId, int count);
}