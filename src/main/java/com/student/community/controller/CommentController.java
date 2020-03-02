package com.student.community.controller;

import com.student.community.dto.CommentDTO;
import com.student.community.enums.CommentTypeEnum;
import com.student.community.enums.StatusCode;
import com.student.community.service.IArticleService;
import com.student.community.service.ICommentService;
import com.student.community.vo.Article;
import com.student.community.vo.Comment;
import com.student.community.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IArticleService articleService;

    //此注解增加事务   为的是协调插入评论与文章回复数更新的数据库操作的事务
    @Transactional
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> insertComment(@RequestBody Comment comment,
                                      HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        comment.setCommentCreateTime(System.currentTimeMillis());
        comment.setCommentUpdateTime(comment.getCommentCreateTime());
        if (comment==null || StringUtils.isBlank(comment.getCommentContent())){
            result.put("code", StatusCode.COMMENT_IS_NULL.getType());
            result.put("msg", StatusCode.COMMENT_IS_NULL.getDesc());
            return result;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("code", StatusCode.USER_IS_NOT_EXISTS.getType());
            result.put("msg", StatusCode.USER_IS_NOT_EXISTS.getDesc());
            return result;
        }
        comment.setCreatorId(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        //判断父级id不为空
        if (comment.getParentId() == 0 || comment.getParentId() == null) {
            result.put("code", StatusCode.UNSELECTED_ARTICLE_FOR_COMMENT.getType());
            result.put("msg", StatusCode.UNSELECTED_ARTICLE_FOR_COMMENT.getDesc());
            return result;
        }
        //判断评论类型
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            result.put("code", StatusCode.TYPE_PARAM_WRONG.getType());
            result.put("msg", StatusCode.TYPE_PARAM_WRONG.getDesc());
            return result;
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcoment = commentService.selectCommentByParentId(comment.getParentId());
            if (dbcoment == null) {
                result.put("code", StatusCode.COMMENT_IS_NOT_EXISTS.getType());
                result.put("msg", StatusCode.COMMENT_IS_NOT_EXISTS.getDesc());
                return result;
            }
            commentService.insertComment(comment);
            result.put("code", StatusCode.SUCCESS.getType());
            result.put("msg", StatusCode.SUCCESS.getDesc());
            return result;
        }
        if (comment.getType() == CommentTypeEnum.ARTICLE.getType()) {
            //回复问题
            Article article = articleService.selectArticleById(comment.getParentId());
            if (article == null) {
                result.put("code", StatusCode.ARTICLE_IS_NOT_EXISTS.getType());
                result.put("msg", StatusCode.ARTICLE_IS_NOT_EXISTS.getDesc());
                return result;
            } else {
                //需要事务管理
                commentService.insertComment(comment);
                articleService.updateArticleCommentCountById(article.getId());
                result.put("code", StatusCode.SUCCESS.getType());
                result.put("msg", StatusCode.SUCCESS.getDesc());
                return result;
            }
        }
        result.put("code", StatusCode.PARAM_ERROR.getType());
        result.put("msg", StatusCode.PARAM_ERROR.getDesc());
        return result;
    }

    @RequestMapping(value = "/selectAllFirstComment", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> selectAllFirstComment(@RequestParam(name = "parentId") Integer parentId,
                                              @RequestParam(name = "type") Integer type,
                                              HttpServletRequest request) {
        Map<String,Object> result=new HashMap<>();
        if (type==null || parentId==null){
            result.put("code", StatusCode.PARAM_ERROR.getType());
            result.put("msg", StatusCode.PARAM_ERROR.getDesc());
            return result;
        }
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            result.put("code", StatusCode.USER_IS_NOT_EXISTS.getType());
            result.put("msg", StatusCode.USER_IS_NOT_EXISTS.getDesc());
            return result;
        }
        List<CommentDTO> commentDTOList=commentService.selectFirstCommentByArticleId(parentId,type);
        result.put("code",StatusCode.SUCCESS.getType());
        result.put("msg",StatusCode.SUCCESS.getDesc());
        result.put("commentDTOList",commentDTOList);
        return result;
    }

}
