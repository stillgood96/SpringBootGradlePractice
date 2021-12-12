package com.springPractice.practiceAlone.repostiroy;

import com.springPractice.practiceAlone.domain.Member;
import com.springPractice.practiceAlone.repositroy.MemberRepository;
import com.springPractice.practiceAlone.repositroy.MemoryMemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

        MemoryMemberRepository repository = new MemoryMemberRepository();

        @AfterEach
        public void afterEacgh() {
                repository.clearStore();
        }

        @Test
        public void save() {
                Member member = new Member();
                member.setName("spring");

                repository.save(member);

                // 반환타입이 Optional 객체이기 때문에 .get() 메서드를 통해서 진행해야 한다.
                Member result = repository.findById(member.getId()).get();

                // 테스트가 잘이뤄졌는지 확인하는 방법으로는 두가지가 있겠다.
                // 한가지는 System.out.print로 뽑아서 확인하는 것이고
                System.out.println( "result = "  + (result ==member));

                // 다른 한가지는 Junit에서 제공하는 Seertions.assertEquals로 인자값으로 비교 대상들을 넣어주면 된다.
                // 두가지를 비교했을때 동일하지 않다면 오류를 뱉어낼 것
                //Assertions.assertEquals(result, member);


                // 또다른 방법
                assertThat(result).isEqualTo(member);

        }


        @Test
        public void findByName() {
                Member member1 = new Member();
                member1.setName("spring1");
                repository.save(member1);

                Member member2 = new Member();
                member2.setName("spring2");
                repository.save(member2);


                Member result = repository.findByName("spring1").get();

                assertThat(result).isEqualTo(member1);

        }


        @Test
        public void findAll() {
                Member member1 = new Member();
                member1.setName("spring1");
                repository.save(member1);


                Member member2 = new Member();
                member2.setName("spring1");
                repository.save(member2);

                List<Member> result = repository.findAll();

                assertThat(result.size()).isEqualTo(2);
        }


}
