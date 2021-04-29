package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.DeliveryInfo;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.Orderer;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    /**
     * dto to entity
     */
    public Order mapFrom(Orderer orderer, OrderRequest orderRequest) {
        DeliveryInfo deliveryInfo = getDeliveryInfo(orderRequest.getDeliveryInfo());
        Order order = new Order(
                orderer,
                deliveryInfo,
                orderRequest.getOrderItems().stream()
                        .map(this::toOrderItem)
                        .toArray(OrderItem[]::new));

        return order;
    }

    private DeliveryInfo getDeliveryInfo(OrderRequest.DeliveryInfo deliveryInfoDto) {
        return new DeliveryInfo(deliveryInfoDto.getAddress(), deliveryInfoDto.getMessage(), deliveryInfoDto.getPhone());
    }

    private OrderItem toOrderItem(OrderRequest.OrderItem orderItemDto) {
        return new OrderItem(
                orderItemDto.getMenuId(),
                orderItemDto.getPrice(),
                orderItemDto.getQuantity(),
                orderItemDto.getOrderOptionGroups().stream()
                        .map(this::toOrderOptionGroup)
                        .toArray(OrderOptionGroup[]::new));
    }

    private OrderOptionGroup toOrderOptionGroup(OrderRequest.OrderOptionGroup orderOptionGroupDto) {
        return new OrderOptionGroup(
                orderOptionGroupDto.getOptionGroupId(),
                orderOptionGroupDto.getOrderOptionItems().stream()
                        .map(this::toOrderOptionItem)
                        .toArray(OrderOptionItem[]::new));
    }

    private OrderOptionItem toOrderOptionItem(OrderRequest.OrderOptionItem orderOptionItemDto) {
        return new OrderOptionItem(orderOptionItemDto.getOptionItemId(), orderOptionItemDto.getPrice());
    }
}
