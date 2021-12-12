package com.springPractice.practiceAlone.controller;

import com.springPractice.practiceAlone.domain.Member;
import com.springPractice.practiceAlone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 빈의 DI 방법 1. 필드주입
//    @Autowired
    private  MemberService memberService;

//    // 빈의 DI 방법 2. 생성자주입 (가장 권장되는 방법)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 빈의 DI 방법 3. Setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
