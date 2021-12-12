package com.springPractice.practiceAlone.controller;

import com.springPractice.practiceAlone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    // 빈의 DI 방법 1. 필드주입
    @Autowired
    private  MemberService memberService;

//    // 빈의 DI 방법 2. 생성자주입 (가장 권장되는 방법)
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 빈의 DI 방법 3. Setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
