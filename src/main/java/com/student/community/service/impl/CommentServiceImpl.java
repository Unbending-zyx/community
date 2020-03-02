package com.student.community.service.impl;

import com.student.community.dao.ICommentDAO;
import com.student.community.dto.CommentDTO;
import com.student.community.service.ICommentService;
import com.student.community.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public void insertComment(Comment comment) {
        commentDAO.insertComment(comment);
    }

    @Override
    public Comment selectCommentByParentId(Integer id) {
        return commentDAO.selectCommentByParentId(id);
    }

    @Override
    public List<CommentDTO> selectFirstCommentByArticleId(Integer id,Integer type) {
        return commentDAO.selectFirstCommentByArticleId(id,type);
    }
}
