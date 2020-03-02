package com.student.community.service;

import com.student.community.dto.ArticleDTO;
import com.student.community.vo.Article;
import com.student.community.vo.User;

import java.util.List;

public interface IArticleService {
    /**
     * 插入文章
     *
     * @param article
     * @return
     */
    int insertArticle(Article article);

    /**
     * 查找所有文章
     *
     * @return
     */
    List<Article> selectAllArticle();

    /**
     * 查找所有文章条数
     *
     * @return
     */
    int selectArticleCount();

    /**
     * 查找所有文章及对应的用户信息
     *
     * @return
     */
    List<ArticleDTO> selectAllArticleDTO();

    /**
     * 查找所有文章条数
     *
     * @return
     */
    int selectArticleCountByUserId(int creatorId);

    /**
     * 根据用户ID查找所有文章
     *
     * @return
     */
    List<ArticleDTO> selectAllArticleDTOByUserId(User user);

    /**
     * 根据文章ID查找文章及用户信息
     *
     * @return
     */
    ArticleDTO selectArticleDTOById(Integer id);

    /**
     * 根据文章ID查找文章
     *
     * @param id
     * @return
     */
    Article selectArticleById(Integer id);

    /**
     * 根据id更新文章
     *
     * @param article
     * @return
     */
    int updateArticleById(Article article);

    /**
     * 根据Id修改阅读数
     * @return
     */
    int updateArticleReadingCountById(int id);

    /**
     * 根据Id修改评论数  默认加1方法
     * @return
     */
    int updateArticleCommentCountById(int id);

}
