## [목록으로](README.md)

## 6. 블로그 기획하고 API 만들기

### 6.1 사전 지식: API와 REST API

### 6.1.1 식당으로 알아보는 API

* API는 클라이언트의 요청을 서버에 전달하고, 서버의 결과물을 클라이언트에게 잘 돌려주는 역할

### 6.1.2 웹의 장점을 최대한 활용하는 REST API

* REST는 Representational State Transfer 로, 자원을 이름으로 구분해 자원의 상태를 주고받는 API 방식 (URL 설계 방식)

[REST API의 특징]

* 서버/클라이언트 구조, 무상태, 캐시 처리 가능, 계층화, 인터페이스 일관성 특징

[REST API의 장점과 단점]

* 장점
  * URL만 보고도 무슨 행동을 하는 API인지 명확하게 이해
  * 상태가 없다는 특징이 있어서 클라이언트와 서버의 역할이 명확하게 분리
  * HTTP 표준을 사용하는 모든 플랫폼에서 사용
* 단점
  * HTTP 메서드, 즉 GET, POST와 같은 방식의 개수에 제한
  * 설계를 하기 위해 공식적으로 제공되는 표준 규약 미존재

<u>REST API를 사용하는 방법</u>

1. 규칙1. URL에는 동사를 쓰지 말고, 자원을 표시해야 한다.
   * (적합) /articles/1
   * (부적합) /articles/show/1, /show/articles/1
2. 동사는 HTTP 메서드로
   * GET /articles/1 - id 가 1인 블로그 글을 조회하는 API
   * POST /articles/1 - 블로그 글을 추가하는 API
   * PUT /articles/1 - 블로그 글을 수정하는 API
   * DELETE /articles/1 - 블로그 글을 삭제하는 API

### 6.2 블로그 개발을 위한 엔티티 구성하기

* 가장 먼서 엔티티를 구성하고, 구성한 엔티티를 사용하기 위한 리포지터리 추가

#### 6.2.1 프로젝트 준비하기

* 이전 작성한 코드 삭제

#### 6.2.2 엔티티 구성하기

* 엔티티 구성

1. domain 패키지를 새로 만들고 Article.java 파일 생성
2. 롬복을 사용해서 코드 수정
    * @NoArgsConstructor 애너테이션을 선언해 접근 제어자가 protected 인 기본 생성자를 별도의 코드 없이 생성
    * @Getter 애너테이션으로 클래스 필드에 대해 별도 코드 없이 생성자 메서드 생성

#### 6.2.3 리포지터리 만들기

1. repository 패키지를 새로 만들고, BlogRepository.java 파일 생성해 BlogRepository 인터페이스 생성

### 6.3 블로그 글 작성을 위한 API 구현하기

* 엔티티 구성이 끝났으니, API 구현
* 구현 과정은 서비스 클래스에 메서드를 구현하고, 컨트롤러에서 사용할 메서드를 구현한 다음, API 테스트 진행

#### 6.3.1 서비스 메서드 코드 작성하기

* 먼저 서비스 계층에 블로그에 글을 추가하는 코드 작성
* 서비스 계층에서 요청을 받을 객체인 AddArticleRequest 객체를 생성하고, BlogService 클래스를 생성한 다음에 블로그 글 추가 메서드인 save() 구현

1. dto 패키지를 생성한 다음, AddArticleRequest.java 파일 생성
    * DTO 는 계층끼리 데이터를 교환하기 위해 사용하는 객체
    * DTO 는 단순하게 데이터를 옮기기 위해 사용하는 전달자 역할을 하는 객체이기 때문에 별도의 비즈니스 로직을 포함하지 않음
2. service 패키지를 생성한 뒤, BlogService.java 를 생성해 BlogService 클래스 구현
    * @RequiredArgsConstructor 는 빈을 생성자로 생성하는 롬복에서 지원하는 애너테이션
    * @Service 애너테이션은 해당 클래스를 빈으로 서블릿 컨테이너에 등록
    * save() 메서드는 JpaRepository 에서 지원하는 저장 메서드 save() 로 AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장

#### 6.3.2 컨트롤러 메서드 코드 작성하기

* URL 에 매핑하기 위한 컨트롤러 메서드 추ㅏ
* /api/articles 에 POST 요청이 오면 @PostMapping 을 이용해 요청을 매핑한 뒤, 블로그 글을 생성하는 BlogService 의 save() 메서드를 호출한 뒤, 생성된 블로그 글을 반환하는 작업을 할 addArticle() 메서드 작성

1. controller 패키지를 생성한 뒤, BlogApiController.java 파일 생성

> 꼭 알아두면 좋을 응답 코드  
>   * 200 OK: 요청이 성공적으로 수행되었음
>   * 201 Created: 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
>   * 400 Bad Request: 요청 값이 잘못되어 요청에 실패했음
>   * 403 Forbidden: 권하이 없어 요청에 실패했음
>   * 404 Not Found: 요청 값으로 찾은 리소스가 없어 요청에 실패했음
>   * 500 Internal Server Error: 서버 상에 문제가 있어 요청에 실패했음

#### 6.3.3 API 실행 테스트 하기

* 실제 데이터를 확인하기 위해 H2 콘솔을 활성화

1. application.yml 수정
2. 포스트맨을 실행하고 http://localhost:8080/api/articles 저장 테스트
3. localhost:8080/h2-console 접속 (JDBC URL 확인)
4. SELECT * FROM ARTICLE 쿼리 입력 후 저장된 데이터 확인

#### 6.3.4 반복 작업을 줄여 줄 테스트 코드 작성하기

1. BlogApiController 클래스 테스크 코드 생성
2. 블로그 글 생성 API 테슽 코드 작성
    * Given: 블로그 글 추가에 필요한 요청 객체 생성
    * When: 블로그 글 추가 API 에 요청 전송. 이때 요청 타입은 JSON 이며, given 절에서 미리 만들어둔 객체를 요청 본문으로 전송
    * Then: 응답코드가 201 Created 인지 확인. Blog 를 전체 조회해 크기가 1인지 확인하고, 실제로 저장된 데이터와 요청 값 비교
3. 테스트 코드 실행하여 코드가 잘 동작하는지 확인


