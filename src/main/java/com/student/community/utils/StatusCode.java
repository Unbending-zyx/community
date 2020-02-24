package com.student.community.utils;

public enum StatusCode {
    SUCCESS("200","成功"),
    ARTICLE_IS_NOT_EXISTS("101","文章不存在");

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
