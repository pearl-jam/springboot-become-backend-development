## [목록으로](README.md)

## 7. 블로그 화면 구성하기

* 타임리프는 템플릿 엔진
* 템플릿 엔진은 스프링 서버에서 데이터를 받아

### 7.1 템플릿 엔진 개념 잡기

#### 7.1.1 템플릿 엔진 개념 잡기

* 타임리프 표현식

|표현식|설명|
|---|---|
|${...}|변수의 값 표현식|
|#{...}|속성 파일 값 표현식|
|@{...}|URL 표현식|
|*{...}|선택한 변수의 표현식. th:object에서 선택한 객체에 접근|

* 타임리프 문법

|표현식|설명|예제|
|---|---|---|
|th:text|텍스트를 표현할 때 사용|th:text=${person.name}|
|th:each|릴렉션을 반복할 때 사용|th:each="person:${persons}"|
|th:if|조건이 true 인 때만 표시|th:if="${person.age}>=20"|

#### 7.1.2 타임리프 사용을 위한 의존성 추가하기

1. build.gradle 의존성 추가하고 그레이들 변경사항 적용

#### 7.1.3 타임리프 문법 익히기용 컨트롤러 작성하기

1. controller 패키지에 ExampleController.java 파일 추가
   * 컨트롤러는 모델을 통해 데이터를 설정하고, 모델은 뷰로 데이터를 전달해 키에 맞는 데이터를 뷰에서 조회 (모델은 컨트롤러와 뷰의 중간다리 역할)

#### 7.1.4 뷰 작성하기

1. src/main/resources/templates 디렉토리에 example.html 파일 생성

#### 7.1.5 뷰 테스트 하기

1. http://localhost:8080/thymeleaf/example 접속

### 7.2 블로그 글 목록 뷰 구현하기

#### 7.2.1 컨트롤러 메서드 작성하기

* 요청을 받아 사용자에게 뷰를 보여주려면 뷰 컨트롤러가 필요
* 뷰 컨트롤러 메서드는 뷰의 이름을 반환하고, 모델 객체에 값을 처리

1. dto 패키지에 ArticleListViewResponse.java 파일 생성
2. controller 패키지에 BlogViewController.java 파일 생성

#### 7.2.2 HTML 뷰 만들고 테스트하기

1. resource/templates 디렉터리에 articleList.html 만들고 모델에 전달한 블로그 글 리스트 개수만큼 반복해 글 정보를 보여주는 코드 작성
2. http://localhost:8080/articles 접속

### 7.3 블로그 글 뷰 구현하기

#### 7.3.1 엔티티에 생성, 수정 시간 추가하기

1. Article.java 필드 추가
2. data.sql 수정
3. SpringBootDeveloperApplication.java created_At, updated_at 자동 업데이트를 위한 애너테이션 추가

#### 7.3.2 컨트롤러 메서드 작성하기

1. ArticleViewResponse.java 생성
2. BlogViewController.java getArticle() 메서드 추가

#### 7.3.3 HTML 뷰 만들기

1. resource/templates 디렉터리에 article.html 생성
2. articleList.html 보러 가기 링크 수정

#### 7.3.4 실행 테스트하기

1. http://localhost:8080/articles 접속

### 7.4 삭제 기능 추가하기

#### 7.4.1 삭제 기능 코드 작성하기

1. src/main/resources/static 디렉토리에 js 디렉토리를 만들고 article.js 파일 생성
2. article.html 에 article.js 임포트

#### 7.4.2 실행 테스트하기

1. http://localhost:8080/articles/1 접속 후 삭제 테스트

### 7.5 수정/생성 기능 추가하기

#### 7.5.1 수정/생성 뷰 컨트롤러 작성하기

1. BlogViewController.java newArticle() 메서드 추가

#### 7.5.2 수정/생성 뷰 만들기

1. resource/templates 디렉터리에 newArticle.html 생성
2. static 디렉터리에 article.js 이벤트 처리 추가
3. article.html 클릭 이벤트 추가

#### 7.5.3 실행 테스트하기

1. http://localhost:8080/articles/1 접속 후 수정 테스트