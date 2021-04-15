package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderCommandServiceTest {

    @InjectMocks
    private final OrderCommandService orderCommandService;
    @Mock
    private final OrderRepository orderRepository;

    public OrderCommandServiceTest(OrderCommandService orderCommandService,
                                   OrderRepository orderRepository) {
        this.orderCommandService = orderCommandService;
        this.orderRepository = orderRepository;
    }

    public void createOrderSuccessTest() {
        // given
        final long ordererId = 1;
        final String address = "seoul";
        final String message = "-";
        final String phone = "010-1234-1234";
        final long menuId = 11;
        final long price = 10_000;
        final int quantity = 2;

        OrderRequestDto.OrderItem orderItems = new OrderRequestDto.OrderItem(menuId, price, quantity);
        OrderRequestDto.DeliveryInfo deliveryInfo = new OrderRequestDto.DeliveryInfo(address, message, phone);
        OrderRequestDto orderRequestDto = new OrderRequestDto(ordererId, deliveryInfo, orderItems);

        // when
        orderCommandService.order(orderRequestDto);

        // then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        Order order = orderCaptor.getValue();
        assertThat(order, notNullValue());
        // TODO: assert
    }
}
