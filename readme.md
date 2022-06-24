# Kakaopay Membership Service

### 카카오페이 과제 

기간 : 2022. 06. 20(월) ~ 2022. 06. 27(월)

* [통합 바코드 발급 API]
* [포인트 적립 API]
* [포인트 사용 API]
* [기간별 내역 조회 API]

### 각 API 프로젝트 공통 스펙
- 언어 / 버전 : java / 11
- 프레임워크 : Spring boot 2.7.0
- RDBMS : Mariadb 10.4 (docker container)

#### 설계 내용

- DB는 총 Member, Store, StoreType, Point, Actionlog 테이블로 나눠 설계했으며 log 테이블은 후에 맴버의 활동 추적이나 포인트 사용 출처 및 추적을 위해 생성하였습니다.
- 



* * *
#### 기본 요구사항에 대한 설계 이유

1) 구현한 어플리케이션이 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록 설계
+ 설계방식 및 이유 : 
2) 맴버십바코드는 공유가 가능해서 동일 바코드에 의한 사용, 적립 요청이 동시간에 들어올 경우도 고여해야한다.
+ 설계방식 및 이유 : @Transactional 을 DB에 Create, Update, Delete 메서드 단위로 걸어 트랜잭션을 나눠 관리하여 커밋, 롤백 트랜잭션 단위를 명확하게 나눠서 관리하여 동시 요청시 데이터 정합성을 고려하였습니다.







