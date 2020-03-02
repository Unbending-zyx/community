package com.student.community.dao;

import com.student.community.dto.CommentDTO;
import com.student.community.vo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommentDAO {

    /**
     * 插入评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 根据parentId查找评论
     * @param id
     * @return
     */
    Comment selectCommentByParentId(@Param("id") Integer id);

    /**
     * 根据文章id查出一级的评论
     * @param id
     * @return
     */
    List<CommentDTO> selectFirstCommentByArticleId(@Param("id") Integer id,@Param("type") Integer type);


}
