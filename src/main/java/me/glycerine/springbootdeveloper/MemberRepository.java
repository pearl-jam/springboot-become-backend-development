package me.glycerine.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 리포지토리는 엔티티에 있는 데이터들을 조회하거나 저장, 변경, 삭제를 할 때 사용하는 인터페이스
// JpaRepository 클래스를 상속받을 때, 엔티티 Member 와 엔티티의 기본키 타입 Long 을 인수로 설정
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
