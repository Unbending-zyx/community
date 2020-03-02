package com.student.community.vo;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Integer type;
    private Integer creatorId;
    private Long commentCreateTime;
    private Long commentUpdateTime;
    private Integer likeCount;
    private String commentContent;
    private Integer parentId;
    private Integer commentCount;
}
