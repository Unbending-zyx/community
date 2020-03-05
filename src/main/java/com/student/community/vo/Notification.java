package com.student.community.vo;

import lombok.Data;

@Data
public class Notification {
    private Integer id;
    private Integer senderId;//发送者
    private String senderName;//发送者昵称
    private Integer receiverId;//接受者
    private Integer outerId;//操作的父级Id   如给一个文章评论  该outerId就是文章的id  评论同理
    private String outerTitle;//操作的父级标题
    private Integer type;
    private Long notificationCreateTime;
    private Integer status;


}
