---
title: 주문 서비스
---

주문 서비스에서는 주문 조회와 주문하기와 주문 취소하기 기능이 있다.  

```java
public interface OrderService {
    List<OrderDto> getOrders(long memberId);
    OrderDto getOrder(long orderId);
    void order(OrderRequestDto orderRequestDto);
    void cancelOrder(long orderId);
}
```
하나의 서비스에서 너무 많은 기능을 하거나 복잡해 진다면 기능 단위로 분리하자  
```java
/** 주문 조회 서비스 */
public interface OrderQueryService {
    List<OrderDto> getOrders(long memberId);
    OrderDto getOrder(long orderId);
}

/** 주문 처리 서비스 */
public interface OrderCommandService {
    void order(OrderRequestDto orderRequestDto);
    void cancelOrder(long orderId);
}
```

### 주문하기
> 주문정보를 가지고 주문하기를 실행하면 주문 객체가 생성(저장)된다.  

이렇게 동작하도록 테스트를 작성해보자.  

```java
public class OrderCommandServiceTest {
    
    private final OrderCommandService orderCommandService;
    private final OrderRepository orderRepository;

    public OrderCommandServiceTest(OrderCommandService orderCommandService,
                                   OrderRepository orderRepository) {
        this.orderCommandService = orderCommandService;
        this.orderRepository = orderRepository;
    }

    public void createOrderSuccessTest() {
        // given
        // TODO: mock
        OrderRequestDto orderRequestDto = new OrderRequestDto();

        // when
        orderCommandService.order(orderRequestDto);

        // then
        // TODO: orderRepository capture
        assertThat();

    }
}
```