package com.student.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/publish")
@Controller
public class PublishController {
    @GetMapping("/show")
    public String publish(){
        return "publish";
    }
}
