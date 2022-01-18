package com.example.springboot.web;

import com.example.springboot.web.dto.MailDto;
import com.example.springboot.web.dto.TestMailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MailController.class)
public class senderTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void socket_test() throws Exception {
        String socket = "socket";

        mvc.perform(get("/socket")
                        .param("id", "id")
                        .param("msg", "msg")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(socket));
    }

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void sendTestMail() throws Exception {

        mvc.perform(post("/sendTestMail"))
                .andExpect(content().string("success"));
    }

    @Test
    public void sendMail() throws Exception {
        final TestMailDto mailDto = new TestMailDto(
                "siznorias@gmail.com",
                new ArrayList<String>(),
                "정상작동한답미까?",
                "<html>도 스트링이 될까?"
        );
        mailDto.addTo("siznorias@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(mailDto);
        mvc.perform(post("/sendMail")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
                )
                .andExpect(content().string("success"));
    }
}
