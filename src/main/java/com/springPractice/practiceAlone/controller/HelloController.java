package com.springPractice.practiceAlone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

        // localhost:8080/hello url에 mapping 을 들어오면 처리해줌
        @GetMapping("hello")
        public String hello(Model model) {
            model.addAttribute("data", "Hello !");

            return "hello";
        }
}
