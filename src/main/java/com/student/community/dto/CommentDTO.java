package com.student.community.dto;

import com.student.community.vo.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Integer type;
    private Integer creatorId;
    private Long commentCreateTime;
    private Long commentUpdateTime;
    private Integer likeCount;
    private String commentContent;
    private Integer parentId;
    private Integer commentCount;
    private User user;

}
