package com.student.community.service;

import com.student.community.dto.ArticleDTO;
import com.student.community.vo.Article;
import com.student.community.vo.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 使用标签查找文章的相关文章
     * @param id  当前文章id
     * @param tag 文章的标签   例   "Spring|SpringBoot|Java"  这种格式的  因为sql语句使用正则进行匹配
     * @return
     */
    List<Article> selectArticleByTags(int id,String tag);

    /**
     * 这是搜索文章的按钮的功能
     * @param selectArticleText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String,Object> selectArticleDTOByTitleLike(String selectArticleText,int pageNum,int pageSize);
}
