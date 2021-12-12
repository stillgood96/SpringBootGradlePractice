package com.springPractice.practiceAlone.service;

import com.springPractice.practiceAlone.domain.Member;
import com.springPractice.practiceAlone.repositroy.MemberRepository;
import com.springPractice.practiceAlone.repositroy.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());

        // result가 Optional로 감싸져 있기 때문에 사용 가능한 ifPresent()
        // 안에 값이 있다면 실행될 콜백함수의 개념 Optional을 사용하지 못하는 경우에는 if 문을 사용했을 것
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // result Optional 객체를 만들어서 할당하는 것은 이쁘지 않다.
        // memberRepository.findByName(member.getName())의 반환 객체는 Optional이기 때문에
        // 바로 ifPreset메서드를 사용할 수 있다.
        //memberRepository.findByName(member.getName());
        // .ifPresent( m -> {
        //      throw new IllegarStateException("이미 존재하는 회원입니다.");
        // });
        // 이렇게 하는 기능은 메서드로 따로 빼놓는 것이좋다 ctrl + alt + shift + T 를 통해서 생성된 로직을 메서드로 생성해주는 기능이 존재한다.
        validateDupliacteMember(memberRepository.findByName(member.getName()), "이미 존재하는 회원입니다. ");


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupliacteMember(Optional<Member> byName, String s) {
        byName
                .ifPresent(m -> {
                    throw new IllegalStateException(s);
                });
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers() {
            return memberRepository.findAll();
    }

    public Optional<Member> findOne (Long memberId) {
        return memberRepository.findById(memberId);
    }
}
