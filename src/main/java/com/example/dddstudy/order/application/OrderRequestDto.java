package com.example.dddstudy.order.application;

import java.util.Arrays;
import java.util.List;

public class OrderRequestDto {
    private long ordererId;
    private DeliveryInfo deliveryInfo;
    private List<OrderItem> orderItems;

    public OrderRequestDto(long ordererId, DeliveryInfo deliveryInfo, OrderItem... orderItems) {
        this.ordererId = ordererId;
        this.deliveryInfo = deliveryInfo;
        this.orderItems = Arrays.asList(orderItems);
    }

    public static class DeliveryInfo {
        private String address;
        private String message;
        private String phone;

        public DeliveryInfo(String address, String message, String phone) {
            this.address = address;
            this.message = message;
            this.phone = phone;
        }
    }

    public static class OrderItem {
        private long menuId;
        private long price;
        private int quantity;
        private List<OrderOptionGroup> orderOptionGroups;

        public OrderItem(long menuId, long price, int quantity, OrderOptionGroup... orderOptionGroups) {
            this.menuId = menuId;
            this.price = price;
            this.quantity = quantity;
            this.orderOptionGroups = Arrays.asList(orderOptionGroups);
        }
    }

    public static class OrderOptionGroup {
        private long optionGroupId;
        private List<OrderOptionItem> orderOptionItems;

        public OrderOptionGroup(long optionGroupId, OrderOptionItem... orderOptionItems) {
            this.optionGroupId = optionGroupId;
            this.orderOptionItems = Arrays.asList(orderOptionItems);
        }
    }

    public static class OrderOptionItem {
        private long optionItemId;
        private long price;

        public OrderOptionItem(long optionItemId, long price) {
            this.optionItemId = optionItemId;
            this.price = price;
        }
    }
}
