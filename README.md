# petmily-server



### 1. 개요

Petmily 모바일 어플리케이션의 메인 서버이다. 

통신 대상은 클라이언트와 Flask 서버 이고, 모든 기능은 REST API를 기반으로 한다.



### 2. 개발 환경

* AWS EC2 인스턴스, RDS
* IntelliJ IDEA
* Spring Boot

​	build.gradle

```
buildscript {
    ext {   
        springBootVersion = '2.1.7.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}
```

* Java 11
* MySQL 8.0.27





### 3. 기능



각 패키지별 기능

|   패키지   |                           기능                           |
| :--------: | :------------------------------------------------------: |
|   client   |                     Flask 서버 연결                      |
| controller | 외부 요청 응답과 파일처리. 데이터 이동간 사용될 객체생성 |
|   domain   |                          DB접근                          |
|  service   |            내부 서비스 로직과 트랜잭션 스케줄            |



