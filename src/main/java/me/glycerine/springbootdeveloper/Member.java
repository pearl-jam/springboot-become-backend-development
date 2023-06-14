package me.glycerine.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Entity 애너테이션은 Member 객체를 JPA가 관리하는 엔티티로 지정
// @Entity 속성 중에 name 을 사용하면 name 값을 가진 테이블 이름과 매핑되고 테이블 이름을 지정하지 않으면 클래스 이름과 같은 이름의 테이블과 매핑
@Entity(name = "member_list")
// protected 기본 생성자
// 엔티티는 반드시 기본 생성자가 있어야 하고, 접근 제어자는 pubilc 또는 proteced 여야 함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member {
    // @Id 는 Long 타입의 id 필드를 테이블의 기본키로 지정
    @Id
    // @GeneratedValue 는 기본키의 생성 방식을 결정
    // - AUTO: 선택한 데이터베이스 방언에 따라 방식을 자동으로 선택 (기본값)
    // - IDENTITY: 기본 키 생성을 데이터베이스에 위임 (=AUTO_INCREMENT)
    // - SEQUENCE: 데이터베이스 시퀀스를 사용해서 기본 키를 할당하는 방법. 오라클에서 주로 사용
    // - TABLE: 키 생성 테이블 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column 애너테이션은 데이터베이스의 컬럼과 필드를 매핑
    // - name: 필드와 매핑할 칼럼 이름. 설정하지 않으면 필드 이름으로 지정
    // - nullable: 컬럼의 null 허용 여부. 설정하지 않으면 true(nullable)
    // - unique: 컬럼의 유일한 값(unique) 여부. 설정하지 않으면 false(non-unique)
    // - columnDefinition: 컬럼 정보 설정. default 값 지정 가능
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
