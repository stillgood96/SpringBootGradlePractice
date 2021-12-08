package com.springPractice.practiceAlone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

        // localhost:8080/hello url에 mapping 을 들어오면 처리해줌
        @GetMapping("hello")
        public String hello(Model model) {
            model.addAttribute("data", "Hello !");

            return "hello";
        }

        @GetMapping("hello-mvc")
        public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
            model.addAttribute("name", name);

            return "hello-template";
        }
}
