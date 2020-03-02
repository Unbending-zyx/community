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

}
