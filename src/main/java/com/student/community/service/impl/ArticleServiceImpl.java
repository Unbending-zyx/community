package com.student.community.service.impl;

import com.student.community.dao.IArticleDAO;
import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
import com.student.community.vo.Article;
import com.student.community.vo.User;
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

    @Override
    public int selectArticleCountByUserId(int creatorId) {
        return articleDAO.selectArticleCountByUserId(creatorId);
    }

    @Override
    public List<ArticleDTO> selectAllArticleDTOByUserId(User user) {
        return articleDAO.selectAllArticleDTOByUserId(user);
    }

    @Override
    public ArticleDTO selectArticleDTOById(Integer id) {
        return articleDAO.selectArticleDTOById(id);
    }

    @Override
    public Article selectArticleById(Integer id) {
        return articleDAO.selectArticleById(id);
    }

    @Override
    public int updateArticleById(Article article) {
        return articleDAO.updateArticleById(article);
    }

    @Override
    public int updateArticleReadingCountById(ArticleDTO articleDTO) {
        return articleDAO.updateArticleReadingCountById(articleDTO);
    }


}
