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

    /**
     * 根据文章ID查找文章及用户信息
     * @return
     */
    ArticleDTO selectArticleDTOById(@Param("id") Integer id);

    /**
     * 根据文章ID查找文章
     * @param id
     * @return
     */
    Article selectArticleById(@Param("id") Integer id);

    /**
     * 根据id更新文章
     * @param article
     * @return
     */
    int updateArticleById(Article article);

    /**
     * 根据Id修改阅读数
     * @return
     */
    int updateArticleReadingCountById(@Param("id")int id);

    /**
     * 根据Id修改评论数  默认加1方法
     * @return
     */
    int updateArticleCommentCountById(@Param("id")int id);

    /**
     * 使用标签查找文章的相关文章
     * @param article  当前文章id
     *     tag 文章的标签   例   "Spring|SpringBoot|Java"  这种格式的  因为sql语句使用正则进行匹配
     * @return
     */
    List<Article> selectArticleByTags(Article article);

    /**
     * 返回title模糊查询条数
     * @param title
     * @return
     */
    int selectArticleCountByTitleLike(@Param("title") String title);

    /**
     * 返回title模糊查询文章及用户对象封装
     * @param title
     * @return
     */
    List<ArticleDTO> selectArticleDTOByTitleLike(@Param("title") String title);
}
