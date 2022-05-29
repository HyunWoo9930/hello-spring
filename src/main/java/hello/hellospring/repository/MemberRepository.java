package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository  {

  Member save(Member member);
 // Optional : 어떠한 값을 찾았을때 그 값을 못찾으면 null 을 반환하는 대신, Optional 로 감싸는 방식.
  Optional<Member> findById(long id);
  Optional<Member> findByName(String name);
  List<Member> findAll();
}
