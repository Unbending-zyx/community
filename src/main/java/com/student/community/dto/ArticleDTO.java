package com.student.community.dto;

import com.student.community.vo.User;
import lombok.Data;

/**
 * 该类作为一个中间传输对象   封装的是文章与用户数据的合并
 */
@Data
public class ArticleDTO {
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
    private User user;
}
