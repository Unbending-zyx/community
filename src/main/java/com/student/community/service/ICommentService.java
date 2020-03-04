package com.student.community.service;

import com.student.community.dto.CommentDTO;
import com.student.community.vo.Comment;

import java.util.List;

public interface ICommentService {
    /**
     * 插入评论
     *
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 根据parentId查找评论
     *
     * @param id
     * @return
     */
    Comment selectCommentByParentId(Integer id);

    /**
     * 根据文章id查出一级的评论
     * @param id
     * @return
     */
    List<CommentDTO> selectFirstCommentByArticleId(Integer id, Integer type);

    /**
     * 根据id修改评论数 评论数加一操作
     * @param id
     * @return
     */
    int updateCommentCommentCountById(Integer id);

    /**
     * 根据id修改评论数 评论数加一操作
     * @param id
     * @return
     */
    int selectSecondCommentCountById(Integer id,Integer type);

    /**
     * 根据id修改点赞数 点赞数加一操作
     * @param id
     * @return
     */
    int updateLikeCount(Integer id);

    /**
     * 获取一级评论的点赞数
     * @param id
     * @return
     */
    int selectLikeCountById(Integer id);

}
