package com.student.community.utils;

import com.student.community.vo.Article;
import com.student.community.vo.User;
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

    public boolean isArticleUserCreated(Article article, User user){
        if (article.getCreatorId()==user.getId()){
            return true;
        }
        return false;
    }
}
