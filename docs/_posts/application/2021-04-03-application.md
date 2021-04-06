---
title: '[애플리케이션]'
---

비즈니스 로직은 존재하지 않고 도메인을 조합하여 기능을 처리하는 영역을 애플리케이션이라고 한다.  
관계의 방향은 애플리케이션에서 도메인으로 간다.(즉, 애플리케이션 역역이 도메인 영역을 의존한다.)  

아래와 같은 관계이다.  
```
+-------------+        +--------+  
| application |------->| domain |  
+-------------+        +--------+  
```
그러나 하나의 도메인으로 많은 기능을 처리할 수 있다.  
```
+-------------+        +--------+        +-------------+  
| application |------->|        |<-------| application |  
+-------------+        |        |        +-------------+  
+-------------+        |        |        +-------------+  
| application |------->| domain |<-------| application |  
+-------------+        |        |        +-------------+  
+-------------+        |        |        +-------------+  
| application |------->|        |<-------| application |  
+-------------+        +--------+        +-------------+  
```
그래서 이것을 아래와 같이 표현 할 수 있다.  
관계의 방향(의존성의 방향)은 밖에서 안쪽으로 향한다.  
```
+-----------------------+  
|      application      |  
|      +----------+     |  
|      |  domain  |     |  
|      +----------+     |  
+-----------------------+  
```

---

디렉토리 구조는 다음과 같다.  
```
└── src
    ├── main
    │   ├── java
    │   │   └── com.example.dddstudy
    │   │       └── DddStudyApplication.java
    │   │       └── order
    │   │           ├── application
    │   │           └── domain
```
