---
title: 주문 완료 이벤트
---

주문하기가 완료되면 여러 후속 작업들이 이뤄진다.  
가게에 알림을 보낸다던지 여러 후속 작업들이 필요할 수 있다.  
이때 도메인 이벤트를 사용하지 않고 코드를 구현해보면 다음과 같다.  
```java
// 응용 서비스
public class OrderService {
    // codes...
    
    public void placeOrder(long ordererId, OrderRequest orderRequest) {
        Order order = orderMapper.mapFrom(ordererId, orderRequest);
        order.place(orderValidator);
        orderRepository.save(order);
        
        // 주문 완료 후 후속 작업들
        storeService.notifyPlaceOrder(order);
    }
}
```
위 코드를 보면 OrderService에서 StoreService에 의존 관계가 생기게 되고  
주문 완료 후 후속 작업들이 많아지게 되면 OrderService에서 더 많은 의존 관계들이 생겨난다.  
그리고 StoreService 혹은 다른 의존 관계가 생긴 서비스에서 변경이 일어나면 OrderService에도 영향이 끼치게 된다.  
이러한 강한 결합도를 낮추기 위한 방법으로 이벤트 발행-구독 모델을 사용하면 된다.  

스프링에서 제공하는 기능으로 도메인에서 AbstractAggregateRoot를 상속 받으면 간편하게 도메인 안에서 이벤트를 발행 할 수 있다.  
주문 완료 시 도메인 이벤트를 발생하는 코드는 다음과 같다.  
```java
public class Order extends AbstractAggregateRoot {
    // codes...

    // 주문하기
    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
        this.status = Status.ORDERED;
        // 이벤트 발행
        registerEvent(new OrderPlacedEvent(this));
    }
}

public class OrderPlacedEvent {
    private Order order;

    public OrderPlacedEvent(Order order) {
        this.order = order;
    }
}
```
이렇게 작성하면 주문 응용서비스에서는 주문 하기 완료 후 후속 작업들에 대한 코드가 필요 없어진다.  
```java
public class OrderService {
    // codes...

    public void placeOrder(long ordererId, OrderRequest orderRequest) {
        Order order = orderMapper.mapFrom(ordererId, orderRequest);
        order.place(orderValidator);
        orderRepository.save(order);
        // 후속 작업들에 대한 코드 필요 없음
    }
}
```
주문 하기 완료 후 이뤄져야 하는 후속 작업들은 주문 하기에서 발생된 주문완료 이벤트를 구독받아서 구현하면 된다.  
스프링에서 제공하는 이벤트 리스너를 사용하면 다음과 같이 간편하게 구현이 가능하다.  
```java
package com.example.dddstudy.store.application;     // 주문 패키지와 다른 가게 패키지

public class OrderPlacedEventHandler {

    @EventListener
    public void handle(OrderPlacedEvent event) {
        System.out.println("주문이 들어왔습니다.");
    }
}
```
가게 응용 서비스 영역에서 주문완료 이벤트를 구독받아 구현한 것이다.  
또한 다른 곳에서도 주문완료 이벤트를 구독받아서 후속 작업을 구현 할 수 있다.  
이렇게 하게되면 결국 주문에서 가게나 다른 도메인에 결합도를 낮추게 되는 효과를 얻을 수 있다.  