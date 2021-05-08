---
title: '[도메인 이벤트]'
---

도메인의 변경이 일어날때 이벤트를 발행시키고 그 이벤트를 구독하는 곳에서 이벤트를 받아 처리한다.

### 도메인 이벤트를 사용하는 이유
발행-구독 모델을 이용하게 되면 결합도를 낮출 수 있다.  
이벤트가 발생되는 곳에서는 이벤트를 발행만 하면 되고 그 이벤트가 발생되고 처리되어야 하는 부분에서 이벤트를 구독받아 이후 로직을 처리하면 되기 때문  

#### 도메인 이벤트를 사용하지 않는 경우 
```java
public class FooService {
    private FooRepository fooRepository;
    private BooService booService;
    private ZooService zooService;
    
    public void run() {
        Foo foo = new Foo();
        // codes...
        fooRepository.save(foo);
        
        boo.runBooByFoo(foo);
        zoo.runZooByFoo(foo);
    }
}
public interface BooService {
    void runBooByFoo(Foo foo);
}
public interface ZooService {
    void runZooByFoo(Foo foo);
}
```
#### 도메인 이벤트를 사용하는 경우
```java
public class Foo {
    public Foo() {
        // codes...
        
        // publish event
        registerEvent(new FooCreatedEvent(this));
    }
}
public class FooService {
    private FooRepository fooRepository;

    public void run() {
        Foo foo = new Foo();
        // codes...
        fooRepository.save(foo);
    }
}
// boo package
public interface FooCreatedEventHandler {
    void handle(FooCreatedEvent fooCreatedEvent);
}
// zoo package
public interface FooCreatedEventHandler {
    void handle(FooCreatedEvent fooCreatedEvent);
}
```
이와 같이 FooService 에서 BooService, ZooService 의 의존관계들이 없어져 낮은 결합도, 높은 응집도를 유지 할 수 있다.  


#### 주문 생성 이벤트
주문이 생성될 때 주문 생성 이벤트를 발생시킨다.  
```java
public class OrderPlacedEvent {
    private Order order;
}
```

#### 주문 생성 이벤트 리스너
주문 생성시 처리되어야 하는 곳에서 주문 생성 이벤트를 감지하여 이벤트가 발생되면 실행된다.  
```java
public interface OrderedEventHandler {
    void ordered(OrderPlacedEvent event);
}
```