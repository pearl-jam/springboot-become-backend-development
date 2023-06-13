package me.glycerine.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 메인 애플리케이션 클래스에 추가하는 애너테이션인 @SpringBootApplicaiton 이 있는 클래스를 찾고 그 클래스에 포함되어 있는 빈을 찾은 다음 테스트용 애플리케이션 컨텍스트 생성
@SpringBootTest
// MockMvc 생성하고 자동으로 구성하는 애너테이션
// - 어플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공하는 유틸리티 클래스
// - 컨트롤러를 테스트할 때 사용되는 클래스
@AutoConfigureMockMvc // MockMvc 생성
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    // 테스트를 실행하기 전에 실행하는 메서드에 적용하는 애너테이션
    // - MockMvc 를 설정
    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // 테스트를 실행한 이후에 실행하는 메서드에 적용하는 애너테이션
    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        // perform() 메서드는 요청을 전송하는 역할을 하는 메서드
        // 결과를 ResultActions 객체를 받으며, ResultActions 객체는 반환 값을 검증하고 확인하는 andExpect() 메서드를 제공
        final ResultActions result = mockMvc.perform(get(url)
                // accept() 메서드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                // andExpect() 메서드는 응답을 검증, isOk 를 사용해 응답 코드가 OK (200) 인지 확인
                .andExpect(status().isOk())
                // jsonPath("$[0].$(필드명)") 은 JSON 응답 값의 값을 가져오는 역할을 하는 메서드
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));

    }
}