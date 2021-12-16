package com.springPractice.practiceAlone.service;

import com.springPractice.practiceAlone.domain.Member;
import com.springPractice.practiceAlone.repositroy.MemberRepository;
import com.springPractice.practiceAlone.repositroy.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

// @Transactional
// 테스트 코드는 반복이 가능해야한다.
// 아래의 회원가입 메서드 테스트시 정상작동한다면 setName을 통한 값이 DB에 들어가게될 것이다.
// 하지만 이후 한번 더 동일한 name으로 테스트하게된다면 테스트는 에러를 뱉어낼 것이다.
// DB에 이미 setName값이 존재하기 때문
// 테스트를 위한 어노테이션 Transactional어노테이션을 이용하면 DB에 값을넣고 테스트가 끝나면 롤백을 해준다.
// 그로인해서 같은 name 값으로 반복적인 테스트가 가능해진다.
// 물론 @beforeEach나 @AfterEach를 통해 삭제하는 로직을 작성해도 되지만 번거롭고 귀찮기 때문 !

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // TestCase 작성할때는 생성자를 통한 @Autowried를 하는 것이 아닌 직접 @Autowired하는 것이 편하다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //  구현체는 SpringConfig파일에서 올라올 것이다.



    @Test
    void 회원가입() {
            // given
            Member member = new Member();
            member.setName("spring");

            // when
            Long saveId = memberService.join(member);

            // then
            Member findMember = memberService.findOne(saveId).get();
            assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {


        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> { memberService.join(member2); });
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }
}