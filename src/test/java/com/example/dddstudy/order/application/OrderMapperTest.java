package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.Orderer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class OrderMapperTest {
    private final OrderMapper orderMapper = new OrderMapper();

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
        Order order = orderMapper.mapFrom(orderer, orderRequest);

        // then
        assertThat(order, notNullValue());
        Orderer actualOrderer = (Orderer) getField(order, "orderer");
        assertThat(getField(actualOrderer, "memberId"), is(ordererId));
        assertThat(getField(order, "status"), is(Order.Status.WAITING));

        OrderRequest.DeliveryInfo actualDeliveryInfo = (OrderRequest.DeliveryInfo) getField(order, "deliveryInfo");
        assertThat(getField(actualDeliveryInfo, "address"), is(address));
        assertThat(getField(actualDeliveryInfo, "message"), is(message));
        assertThat(getField(actualDeliveryInfo, "phone"), is(phone));

        List<OrderItem> actualOrderItems = (List<OrderItem>) getField(order, "orderItems");
        assertThat(actualOrderItems.size(), is(1));
        OrderItem actualOrderItem1 = actualOrderItems.get(0);
        assertThat(getField(actualOrderItem1, "menuId"), is(menuId));
        assertThat(getField(actualOrderItem1, "price"), is(price));
        assertThat(getField(actualOrderItem1, "quantity"), is(quantity));

        List<OrderOptionGroup> orderOptionGroups = (List<OrderOptionGroup>) getField(actualOrderItem1, "orderOptionGroups");
        assertThat(orderOptionGroups.size(), is(1));
        OrderOptionGroup actualOrderOptionGroup = orderOptionGroups.get(0);
        assertThat(getField(actualOrderOptionGroup, "optionGroupId"), is(optionGroupId));

        List<OrderOptionItem> orderOptionItems = (List<OrderOptionItem>) getField(actualOrderOptionGroup, "orderOptionItems");
        assertThat(orderOptionItems.size(), is(1));

        OrderOptionItem actualOrderOptionItem = orderOptionItems.get(0);
        assertThat(getField(actualOrderOptionItem, "optionItemId"), is(optionItemId));
        assertThat(getField(actualOrderOptionItem, "price"), is(optionItemPrice));
    }
}
