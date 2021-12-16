package com.springPractice.practiceAlone.service;

import com.springPractice.practiceAlone.domain.Member;
import com.springPractice.practiceAlone.repositroy.MemoryMemberRepository;
import com.springPractice.practiceAlone.repostiroy.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    // 테스트 클래스 메서드명을 작성할때는 과감하게 한글로 작성해도 된다.
    // 빌드 될때 테스트코드는 포함되지 않는다.
    @Test
    void 회원가입() {
            // given
            Member member = new Member();

            member.setName("hello");

            // when
            Long saveId = memberService.join(member);

            // then
            Member findMember = memberService.findOne(saveId).get();
            assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // 위에서 작성된 회원가입 테스트는 반쪽짜리 테스트이다.
        // 회원가입에서 발생할 수 있는 이름중복에 대한 예외상황에 대해서도 테스트 코드가 작성되어야 한다.

        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

            // 예외상황을 잡는 방법1. try / catch
            try {
                memberService.join(member2);
                fail();
            }catch(IllegalStateException e) {
                // 예외상황 발생시 생기는 메세지가 동일한지 체크를 하는 것.
                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }

            // 예외상황을 잡는 방법2.
            // memberService.join(member2) 메서드를 실행할 건데 IllegalStateException 예외가 터져야 한다!
            assertThrows(IllegalStateException.class, () -> {memberService.join(member2);});

            // IllegalStateException오류 발생시 출력되는 메세지 검증방법
            // assertThrows(IllegalStateException.class, () -> {memberService.join(member2);}); 작성 후  Ctrl + Alt + V 를 하면 객체에다 할당해줌
            // 할당된 객체에 메세지를 assertThat을 통해 검증하면 된다.
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> { memberService.join(member2); });
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findONe() {
    }
}