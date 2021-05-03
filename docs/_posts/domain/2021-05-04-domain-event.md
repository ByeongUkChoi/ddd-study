---
title: '[도메인 이벤트]'
---

도메인의 변경이 일어날때 이벤트를 발행시키고 그 이벤트를 구독하는 곳에서 이벤트를 받아 처리한다.

#### 주문 생성 이벤트
주문이 생성될 때 주문 생성 이벤트를 발생시킨다.  
```java
public class NewOrderCreatedEvent {
    private Order order;
}
```

#### 주문 생성 이벤트 리스너
주문 생성시 처리되어야 하는 곳에서 주문 생성 이벤트를 감지하여 이벤트가 발생되면 실행된다.  
```java
public interface OrderedEventHandler {
    void ordered(NewOrderCreatedEvent event);
}
```