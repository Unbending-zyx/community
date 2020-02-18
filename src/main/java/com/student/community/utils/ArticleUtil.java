package com.student.community.utils;

import org.springframework.stereotype.Component;

@Component
public class ArticleUtil {
    public int buildPageCount(int pageCount,int pageSize){
        if (pageCount % pageSize != 0) {
            pageCount = pageCount / pageSize + 1;
        } else {
            pageCount = pageCount / pageSize;
        }
        return pageCount;
    }
}
