package com.springPractice.practiceAlone;

import com.springPractice.practiceAlone.repositroy.MemberRepository;
import com.springPractice.practiceAlone.repositroy.MemoryMemberRepository;
import com.springPractice.practiceAlone.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
