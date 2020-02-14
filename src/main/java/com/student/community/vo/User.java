package com.student.community.vo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String gitAccountId;
    private String username;
    private String password;
    private String gitName;
    private String gitBio;
    private String accountName;
    private String token;
    private Long createTime;
    private Long updateTime;
    private String avatarUrl;
}
