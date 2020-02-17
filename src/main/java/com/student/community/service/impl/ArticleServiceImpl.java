package com.student.community.service.impl;

import com.student.community.dao.IArticleDAO;
import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
import com.student.community.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDAO articleDAO;
    @Override
    public int insertArticle(Article article) {
        return articleDAO.insertArticle(article);
    }

    @Override
    public List<Article> selectAllArticle() {
        return articleDAO.selectAllArticle();
    }

    @Override
    public int selectArticleCount() {
        return articleDAO.selectArticleCount();
    }

    @Override
    public List<ArticleDTO> selectAllArticleDTO() {
        return articleDAO.selectAllArticleDTO();
    }
}
