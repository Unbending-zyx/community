package com.student.community.vo;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private String title;
    private String description;
    private Long articleCreateTime;
    private Long articleUpdateTime;
    private Integer creatorId;
    private Integer readingCount;
    private Integer likeCount;
    private Integer commentCount;
    private String tag;
}
