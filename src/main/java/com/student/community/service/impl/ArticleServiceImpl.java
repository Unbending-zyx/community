package com.student.community.service.impl;

import com.student.community.dao.IArticleDAO;
import com.student.community.service.IArticleService;
import com.student.community.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDAO articleDAO;
    @Override
    public int insertArticle(Article article) {
        return articleDAO.insertArticle(article);
    }
}
