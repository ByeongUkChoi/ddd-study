package com.example.dddstudy.order.domain;

import com.example.dddstudy.order.application.OrderValidator;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Arrays;
import java.util.List;

public class Order extends AbstractAggregateRoot {
    private Long id;
    private long ordererId;
    @Getter
    private long storeId;
    @Getter
    private List<OrderItem> orderItems;
    private DeliveryInfo deliveryInfo;
    private Status status;

    public Order(long ordererId, long storeId, DeliveryInfo deliveryInfo, OrderItem... orderItems) {
        this.ordererId = ordererId;
        this.storeId = storeId;
        this.orderItems = Arrays.asList(orderItems);
        this.deliveryInfo = deliveryInfo;
    }

    /** 주문하기 */
    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
        this.status = Status.WAITING;
        registerEvent(new OrderPlacedEvent(this));
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
