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
- Swagger 2.0 적용
  (각 API의 application.yml에 상세 접근 주소 정리하였습니다.)

## 설계 내용
###[공통]
- DB는 총 Member, Store, StoreType, Point, Actionlog 테이블로 나눠 설계했으며 log 테이블은 후에 맴버의 활동 추적이나 포인트 사용 출처 및 추적을 위해 생성하였습니다.
- 각 API의 단위 테스트는 기본 서비스 단위 테스트로 성공/실패 대표 케이스 기준으로 DB까지 잘 다녀오는지에 대해 작성해두었습니다.
- RESTFUL 방식의 URL 호출, 메소드에 따른 액션 정의 기준을 따라 개발하였습니다.
- 기본 handshake비용과 프로세스, 스레드 생성/삭제 비용을 줄이려 Hikari Pool 방식을 사용해 DBMS와 연동하였습니다.
- 스프링 AOP 개념을 활용하여 @Transactional 사용 및 @Aspect를 통해 직접 AOP 를 구현하여 적립/사용 액션의 성공 콜백작업으로 
구현함으로써 코드 관리성과 의존도를 낮추고 편리하게 저장관리 할수있도록 구현하였습니다.
- 맴버십바코드는 공유가 가능해서 동일 바코드에 의한 사용, 적립 요청이 동시간에 들어올 경우도 대비하여 @Transactional 을 DB에 
Create, Update, Delete 메서드 단위로 걸어 트랜잭션을 나눠 관리하여 커밋, 롤백 트랜잭션 단위를 명확하게 나눠서 관리하여 동시 요청시 데이터 정합성을 고려하였습니다.


###[통합 바코드 발급 API]

###[포인트 적립 API]

###[포인트 사용 API]

###[내역 조회 API]




* * *





