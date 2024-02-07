package com.study.springboot.api.request;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReplyRequest {
    private Long bno;
    private String content;
    private Member id; 
}