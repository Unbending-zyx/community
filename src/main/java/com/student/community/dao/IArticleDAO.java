package com.student.community.dao;


import com.student.community.dto.ArticleDTO;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IArticleDAO {
    /**
     * 插入文章
     * @param article
     * @return
     */
    int insertArticle(Article article);

    /**
     * 查找所有文章
     * @return
     */
    List<Article> selectAllArticle();

    /**
     * 查找所有文章条数
     * @return
     */
    int selectArticleCount();

    /**
     * 查找所有文章及对应的用户信息
     * @return
     */
    List<ArticleDTO> selectAllArticleDTO();

    /**
     * 查找所有文章条数
     * @return
     */
    int selectArticleCountByUserId(@Param("creatorId") int creatorId);

    /**
     * 根据用户ID查找所有文章
     * @return
     */
    List<ArticleDTO> selectAllArticleDTOByUserId(User user);
}
