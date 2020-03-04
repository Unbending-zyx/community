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

    /**
     * 根据id修改评论数 评论数加一操作
     * @param id
     * @return
     */
    int updateCommentCommentCountById(@Param("id") Integer id);

    /**
     * 查找二级评论总数
     * @param id
     * @return
     */
    int selectSecondCommentCountById(@Param("id") Integer id,@Param("type")Integer type);

    /**
     * 根据id修改点赞数 点赞数加一操作
     * @param id
     * @return
     */
    int updateLikeCount(@Param("id") Integer id);

    /**
     * 获取一级评论的点赞数
     * @param id
     * @return
     */
    int selectLikeCountById(@Param("id") Integer id);



}
