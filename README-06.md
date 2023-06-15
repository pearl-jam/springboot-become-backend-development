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