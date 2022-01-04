package com.example.springboot.web;

import com.example.springboot.web.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MailController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/sendTestMail")
    public String sendTestMail() {
        Sender sender = new Sender();

        List<String> to = new ArrayList<String>();
        to.add("siznorias@gmail.com");
        MailDto mailDto = new MailDto(
                "siznorias@gmail.com",
                to,
                "테스트메일입니다",
                "테스트임 블라블라"
        );

        sender.send(mailDto);

        return "success";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody MailDto mailDto) {
        Sender sender = new Sender();
        sender.send(mailDto);
        return "success";
    }
}
