---
title: 주문 서비스
---

주문 서비스에서는 주문하기와 주문 취소하기 기능이 있다.  

```java
public interface OrderService {
    void order(OrderRequestDto orderRequestDto);
    void cancelOrder(long orderId);
}
```
하나의 서비스에서 너무 많은 기능을 하거나 복잡해 진다면 메서드 단위로 분리하자  
```java
public interface OrderService {
    void order(OrderRequestDto orderRequestDto);
}

public interface OrderCancelService {
    void cancelOrder(long orderId);
}
```
