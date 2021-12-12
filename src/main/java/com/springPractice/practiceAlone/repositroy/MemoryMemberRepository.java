package com.springPractice.practiceAlone.repositroy;

import com.springPractice.practiceAlone.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 동시성 문제 때문에 공유되는 변수에는 ^%&%^& 를 사용해야 한다고 하는데 못들음..
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null 값이 넘어올까봐 빨간줄 에러가나왔는데
        // ofNullable 메서드를 통해 null이 올때 처리해줄 수 있는 내부적으로 돌아가는 로직이 있는 것으로 판단이 된다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // java 8 람다형식
        // store(map) value들을 반복문을 돌린다. 아마 stream이 그 역할을 해주는 것으로 판단이 된다.
        // 이후 filter를 걸쳐서 (Javascript의 Arrow Function이랑 흡사하다.)
        // member 객체의 Name이 매개변수로 받아온 name과 같다면 그 값을 findAny를 통해 반환해주는 것으로 판단이도니다.
        // 없다면 마찬가지로 Optional 안에 null을 담아서 리턴해주는데 Optional을 null처리 방법중 하나라고 생각하자
        return store.values().stream()
                .filter(member -> member.getName().equalsIgnoreCase(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
