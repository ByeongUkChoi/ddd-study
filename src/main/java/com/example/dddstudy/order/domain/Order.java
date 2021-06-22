package com.example.dddstudy.order.domain;

import com.example.dddstudy.global.error.exception.BusinessException;
import com.example.dddstudy.global.error.exception.ErrorCode;
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

    // 주문하기
    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
        this.status = Status.ORDERED;
        registerEvent(new OrderPlacedEvent(this));
    }

    // 결제완료
    public void pay() {
        if (!Status.ORDERED.equals(this.status)) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS_TO_PAY);
        }
        this.status = Status.PAYED;
        registerEvent(new OrderPayedEvent(this));
    }

    // 주문 취소
    public void cancel(long ordererId) {
        if (!enableOrderCancel()) {
            throw new BusinessException(ErrorCode.ORDER_CANNOT_BE_CANCELED);
        }
        if (ordererId != this.ordererId) {
            throw new BusinessException(ErrorCode.INVALID_ORDERER);
        }
        status = Status.CANCELED;
        registerEvent(new OrderCanceledEvent(this));
    }

    // 주문 취소 가능 여부
    private boolean enableOrderCancel() {
        return Status.ORDERED.equals(status);
    }

    public enum Status {
        ORDERED,        // 주문 완료
        PAYED,          // 결제 완료
        PREPARING,      // 준비중
        DELIVERING,     // 배달중
        DELIVERED,      // 배달 완료
        CANCELED        // 취소됨
    }
}
