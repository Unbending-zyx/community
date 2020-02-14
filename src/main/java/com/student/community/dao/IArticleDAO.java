package com.student.community.dao;


import com.student.community.vo.Article;

public interface IArticleDAO {
    /**
     * 插入文章
     * @param article
     * @return
     */
    int insertArticle(Article article);
}
