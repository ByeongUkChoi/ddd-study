package com.example.dddstudy.order.application;

import com.example.dddstudy.menu.domain.Menu;
import com.example.dddstudy.menu.domain.MenuRepository;
import com.example.dddstudy.order.domain.DeliveryInfo;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.store.domain.Store;
import com.example.dddstudy.store.domain.StoreRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderValidatorTest {
    private final OrderValidator orderValidator;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public OrderValidatorTest() {
        storeRepository = mock(StoreRepository.class);
        menuRepository = mock(MenuRepository.class);
        orderValidator = new OrderValidator(storeRepository, menuRepository);
    }

    @Test
    public void orderValidatorTest() {
        // given
        final long ordererId = 1;
        final long storeId = 5;
        final String address = "seoul";
        final String message = "-";
        final String phone = "010-1234-1234";
        final long menuId = 11;
        final String menuName = "Chicken";
        final long price = 10_000;
        final int quantity = 2;
        final long optionGroupId = 3;
        final long optionItemId = 5;
        final long optionItemPrice = 700;

        DeliveryInfo deliveryInfo1 = new DeliveryInfo(address, message, phone);
        OrderOptionItem orderOptionItem1 = new OrderOptionItem(optionItemId, optionItemPrice);
        OrderOptionGroup orderOptionGroup1 = new OrderOptionGroup(optionGroupId, orderOptionItem1);
        OrderItem orderItem1 = new OrderItem(menuId, price, quantity, orderOptionGroup1);
        Order order = new Order(ordererId, storeId, deliveryInfo1, orderItem1);

        // TODO: init store
//        Store store = new Store();
        Menu menu = new Menu(menuName, storeId, price, true);

//        when(storeRepository.findById(eq(storeId))).thenReturn(Optional.of(store));
        when(menuRepository.findById(eq(menuId))).thenReturn(Optional.of(menu));

        // when
        orderValidator.validate(order);
    }
}
