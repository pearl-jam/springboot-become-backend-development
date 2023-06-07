## [목록으로](README.md)

## 3. 스프링 부트 3 구조 이해하기

### 3.1 스프링 부트 3 구조 살펴보기

* 각 계층이 양 옆의 계층과 통신하는 구조
* 계층이라는 것은 각자의 역할과 책임이 있는 소프트웨어의 구성 요소
* 각 계층은 서로 소통할 수는 있지만 다른 계층에 직접 간섭하거나 영향을 미칮지 않음

#### 3.1.1 카페와 빵집으로 이해하는 계층

* 각 계층은 자신의 책임에 맞는 역할(커피 팔기, 빵 팔기)을 수행하며, 필요에 따라 소통(카피 사면 빵 할인)
* 스프링 부트에는 프레젠테이션, 비즈니스, 퍼시슽ㄴ스 계층이 있으며 이 계층이 서로 통신하며 프로그램을 구성

> #### 프레젠테이션 계층
> HTTP 요청을 받고 이 요청을 비즈니스 계층으로 전송하는 역할
> 컨트롤러가 바로 프레젠테이션 계층의 역할
> #### 비즈니스 계층
> 모든 비즈니스 로직을 처리 (서비스를 만들기 위한 로직)
> 주문 서비스라고 한다면 주문 개수, 가격 등의 데이터를 처리하기 위한 로직, 주문 처리를 하다가 발생하는 예외 처리 로직, 주문을 받거나 취소하는 것 같이 프로세스를 구현하기 위한 로직
> #### 퍼시스턴스 계층
> 모든 데이터베이스 관련 로직을 처리
> 이 과정에서 데이터베이스에 접근하는 DAO 객체를 사용
> 리포지토리가 퍼시스턴스 계층의 역할

#### 3.1.2 스프링 부트 프로젝트 디렉터리 구성하며 살펴보기

> #### main
> 실제 코드를 작성하는 공간. 프로젝트 실행에 필요한 소스 코드나 리소스 파일 위치
> #### test
> 프로젝트의 소스 코드를 테스트 할 목적의 코드나 리소스 파일 위치
> #### build.gradle
> 빌드를 설정하는 파일 (의존성이나 플러그인 설정 등과 같이 빌드에 필요한 설정을 할 때 사용)
> #### setting.gradle
> 빌드할 프로젝트의 정보를 설정하는 파일

#### 3.1.3 main 디렉터리 구성하기

1단계. main/resources/templates 디렉토리 생성
2단계. main/resources/static 디렉토리 생성
3단계. main/resources/application.yml 파일 생성

