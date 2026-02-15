package com.shopping.shopping_backend_server.repository;

import com.shopping.shopping_backend_server.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 스프링 부트 환경을 다 불러와서 테스트하겠다는 뜻
public class MemberRepositoryTest {

    @Autowired // 스프링이 만든 Repository를 가져와서 연결해줌
    MemberRepository memberRepository;

    @Test
    public void 회원가입_테스트() {
        Member member = Member.builder()
                .username("user01")         // 아이디
                .password("password123!")   // 비밀번호
                .name("회원 데이터 테스터")    // 이름
                .email("usertester@test.com")  // 이메일
                .phone("010-1234-5678")     // 전화번호
                .address("부산광역시 사상구")  // 주소
                .role("USER")               // 권한 (일반 사용자)
                .build();

        // 저장 실행
        Member savedMember = memberRepository.save(member);

        // 콘솔에서 확인 (Sout)
        System.out.println("저장된 회원 정보: " + savedMember);
    }
}