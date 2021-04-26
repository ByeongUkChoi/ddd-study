---
title: 주문 서비스
---

주문 서비스에서는 주문 조회와 주문하기와 주문 취소하기 기능이 있다.  

```java
public interface OrderService {
    List<OrderDto> getOrders(long memberId);
    OrderDto getOrder(long orderId);
    void order(OrderRequest orderRequest);
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
    void order(OrderRequest orderRequest);
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

    public OrderCommandServiceTest() {
        orderRepository = mock(OrderRepository.class);
        orderCommandService = new OrderCommandService(orderRepository);
    }

    /**
     * 테스트 예시
     */
    @Test
    public void createOrderSuccessTest() {
        // given (테스트를 위한 준비)
        final long orderId;
        OrderRequest orderRequest = new OrderRequset(orderId);

        // when (테스트 하련느 행동 실행)
        orderCommandService.order(orderRequest);

        // then (실행 결과 예상 값 비교)
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        final Order order = orderCaptor.getValue();
        assertThat(order, notNullValue());
        assertThat(getField(order, "ordererId"), is(ordererId));
        
        // TODO: 주문 생성 이벤트 테스트
    }
}
```
#### given-when-then 패턴
BDD(Behavior-Driven Development) 중 하나로 아래와 같은 구조를 가진다.  
given : 테스트를 위한 준비 (테스트를 위한 상태 설정)  
when : 테스트 하려는 행동
then : 실행한 결과의 예상되는 변화 설명

#### Mapper
OrderCommandService 의 Order 메소드에서 해야 할 일은 아래와 같다.  
입력된 정보(OrderRequest)로 주문(Order)를 생성하여 저장한다.  
이때 OrderRequest -> Order 변환은 단순 변환하는 작업이지만 코드 양이 많고 OrderCommandService 와 클래스를 분리 할 수 있다.  
이렇게 dto -> domain 을 변환해 주는 것을 Mapper 라고 하자.  

##### orderMapper
```java
public interface OrderMapper {
    Order mapFrom(OrderRequest orderRequest);
}
```
Mapper 를 사용하여 OrderCommandService 의 가독성이 좋아졌고, 단순 변환 로직은 Mapper 에 위임할 수 있게 되었다.  
```java
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final OderMapper orderMapper;

    public OrderCommandService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper; 
    }

    public void order(OrderRequest orderRequest) {
        Order order = orderMapper.mapFrom(orderRequest);
        orderRepository.save();
    }
}
```

