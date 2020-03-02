package com.student.community.enums;

public enum StatusCode {
    ARTICLE_IS_NOT_EXISTS("101","文章不存在"),
    UNSELECTED_ARTICLE_FOR_COMMENT("110","未选中文章进行评论"),
    TYPE_PARAM_WRONG("111","评论类型错误或不存在"),
    COMMENT_IS_NOT_EXISTS("112","评论不存在"),
    COMMENT_IS_NULL("113","评论内容不能为空"),
    SUCCESS("200","成功"),
    USER_IS_NOT_EXISTS("301","用户未登录"),
    PARAM_ERROR("400","传入参数有误"),
    SELECT_WRONG("401","查询失败请重试");

    private String type;    //类型
    private String desc;    //描述

    StatusCode(String type,String desc){
        this.type=type;
        this.desc=desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
