package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public class MemoryMemberRepository implements  MemberRepository{

  private static Map<Long , Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence); // member의 아이디를 1씩 올려줘서 중복되지 않게 Id 설정.
    store.put(member.getId(), member); // 멤버 Map에 key를 id로 주고, value 값에 멤버를 넣어줘서 Map에 저장.
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
    //store의 value 값들을 돌면서, 멤버의 getName을 했을때 name과 일치하면 모두 반환해줌.
  }

  @Override
  public List<Member> findAll() {
    return  new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}
