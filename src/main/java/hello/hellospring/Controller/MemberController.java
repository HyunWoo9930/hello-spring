package hello.hellospring.Controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

  private final MemberService memberService;

  @Autowired // spring 컨테이너가 관리하는 memberService를 넣어줌
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
    // 생성자 주입 추천
  }

  @GetMapping("/members/new") // 1. 회원 가입을 누르면 이 함수를 불러옴
  public String createForm() {
    return "members/createMemberForm"; // 2. 여기서 template members/createMemberForm 이라는 파일을 찾음.
  }

  @PostMapping("/members/new") // 3. html 파일에서 post members/new 를 등록할때 여기로 넘어옴
  public String create(
      MemberForm form) { // 4. create 함수가 실행될때, 자동으로 Spring에서 MemberForm의 name 값에 setName으로 name값을 넣어줘서 들어옴.
    Member member = new Member();
    member.setName(form.getName());
    memberService.join(member); // 회원 가입

    return "redirect:/"; // home 화면으로 다시 넘어감
  }

  @GetMapping("members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
