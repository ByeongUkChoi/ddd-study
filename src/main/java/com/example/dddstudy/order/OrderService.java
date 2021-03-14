package com.example.dddstudy.order;

import java.util.List;

/**
 * 주문하기 기능
 */
public interface OrderService {
    // 주문하기
    void order();
    // 주문 취소하기
    void cancelOrder();
    // 주문 목록 조회하기
    List<Object> getOrderSnippets();
    // 주문 상세 조회하기
    List<Object> getOrderDetails();
}
