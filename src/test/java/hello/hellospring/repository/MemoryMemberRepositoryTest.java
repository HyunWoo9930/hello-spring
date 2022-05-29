package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    repository.clearStore();
    // class 전체를 테스트 돌릴떄 남은 저장 레포지토리를 clear 해주는 함수
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("hyunwoo");
    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    repository.findByName("hyunwoo");
    assertThat(member).isEqualTo(result);
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
//    assertThat(result).isEqualTo(member2);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    assertThat(repository.findAll().size()).isEqualTo(2);
  }
}
