package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Orderer;
import org.junit.jupiter.api.Test;

public class OrderMapperTest {
    private final OrderMapper orderMapper;

    public OrderMapperTest(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Test
    public void orderMapperTest() {
        // given
        final long ordererId = 1;
        final String orderName = "Choi";

        final String address = "seoul";
        final String message = "-";
        final String phone = "010-1234-1234";
        final long menuId = 11;
        final long price = 10_000;
        final int quantity = 2;
        final long optionGroupId = 3;
        final long optionItemId = 5;
        final long optionItemPrice = 700;

        Orderer orderer = new Orderer(ordererId, orderName);
        OrderRequest.OrderOptionItem orderOptionItem = new OrderRequest.OrderOptionItem(optionItemId,optionItemPrice);
        OrderRequest.OrderOptionGroup orderOptionGroup = new OrderRequest.OrderOptionGroup(optionGroupId, orderOptionItem);
        OrderRequest.OrderItem orderItem1 = new OrderRequest.OrderItem(menuId, price, quantity, orderOptionGroup);
        OrderRequest.DeliveryInfo deliveryInfo = new OrderRequest.DeliveryInfo(address, message, phone);
        OrderRequest orderRequest = new OrderRequest(deliveryInfo, orderItem1);

        // when
        orderMapper.mapFrom(orderer, orderRequest);

        // then
        // TODO: assert
    }
}
