package com.example.dddstudy.order.domain;

import java.util.Arrays;
import java.util.List;

public class Order {
    private Long id;
    private long ordererId;
    private long storeId;
    private List<OrderItem> orderItems;
    private DeliveryInfo deliveryInfo;
    private Status status;

    /** 주문 생성 */
    public Order(long ordererId, long storeId, DeliveryInfo deliveryInfo, OrderItem... orderItems) {
        this.ordererId = ordererId;
        this.storeId = storeId;
        this.orderItems = Arrays.asList(orderItems);
        this.deliveryInfo = deliveryInfo;
        this.status = Status.WAITING;     // <-- 주문 생성 시 대기중 상태로 생성됨
    }

    /** 주문 취소 */
    public void cancel() {
        if (enableOrderCancel()) {
            status = Status.CANCELED;
        }
        throw new RuntimeException();
    }

    /** 주문 취소 가능 여부 */
    private boolean enableOrderCancel() {
        return Status.WAITING.equals(status);
    }

    public enum Status {
        WAITING,        // 대기중 (주문 완료)
        PREPARING,      // 준비중
        DELIVERING,     // 배달중
        DELIVERED,      // 배달 완료
        CANCELED        // 취소됨
    }
}
