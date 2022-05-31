package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
// 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
// 테스트 완료 후에 항상 롤백한다.
// 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  void 회원가입() throws Exception{
    //given
    Member member = new Member();
    member.setName("spring");

    //when
    Long saveId = memberService.join(member);

    //then
    System.out.println("member.getName() = " + member.getName());
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void 중복_회원_예외() {
    //given
    Member member1 = new Member();
    member1.setName("hyunwoo");

    Member member2 = new Member();
    member2.setName("hyunwoo");
    //when
    memberService.join(member1);
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    //then
  }
}