package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    //given
    Member member1 = new Member();
    member1.setName("spring1");

    //when
    long saveId = memberService.join(member1);

    //then
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member1).isEqualTo(findMember);
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


  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}