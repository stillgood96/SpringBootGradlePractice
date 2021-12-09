package com.springPractice.practiceAlone.repositroy;

import com.springPractice.practiceAlone.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional Java 8에 포함되는 기능으로
    // null 처리에 대한 한가지 방법 이라고 한다.
    // MemboryMemberRepository 에서 설명을 적는다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
