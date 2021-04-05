---
title: 주문 서비스
---

주문 서비스에서는 주문하기와 주문 취소하기 기능이 있다.  

```java
public interface OrderService {
    void order();
    void cancelOrder();
}
```