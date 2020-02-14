package com.student.community.service;

import com.student.community.vo.Article;

public interface IArticleService {
    /**
     * 插入文章
     * @param article
     * @return
     */
    int insertArticle(Article article);
}
