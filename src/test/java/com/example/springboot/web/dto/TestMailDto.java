package com.example.springboot.web.dto;

import com.amazonaws.services.simpleemail.model.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TestMailDto {
    public String from;
    public List<String> to = new ArrayList<>();
    public String subject;
    public String content;

    @Builder
    public TestMailDto(String from, List<String> to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public void addTo(String email){
        this.to.add(email);
    }

}