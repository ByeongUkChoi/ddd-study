package com.example.dddstudy.order.domain;

import java.util.List;

public class Order {
    private Long id;
    private Orderer orderer;
    private List<OrderItem> orderItems;
    private DeliveryInfo deliveryInfo;
    private Status status;

    /** 주문 생성 */
    public void order(Orderer orderer, List<OrderItem> orderItems, DeliveryInfo deliveryInfo) {
        this.orderer = orderer;
        this.orderItems = orderItems;
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

    enum Status {
        WAITING, PREPARING, DELIVERING, DELIVERED, CANCELED
    }
}
