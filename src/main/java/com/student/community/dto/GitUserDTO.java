package com.student.community.dto;

import lombok.Data;

@Data
public class GitUserDTO {
    private Long id;
    private String name;
    private String bio;//描述
    private String avatar_url;
}
