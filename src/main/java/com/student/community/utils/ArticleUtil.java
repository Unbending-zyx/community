package com.student.community.utils;

import com.student.community.dao.IArticleDAO;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleUtil {

    @Autowired
    private IArticleDAO articleDAO;

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

    /**
     * 根据id判断文章是否存在
     */
    public boolean isArticleExistsById(Integer id){
        Article article=articleDAO.selectArticleById(id);
        if (article==null){
            return false;
        }else{
            return true;
        }
    }
}
