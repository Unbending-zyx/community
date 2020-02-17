package com.student.community.controller;

import com.student.community.service.IArticleService;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/publish")
@Controller
public class PublishController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/show")
    public String publish() {
        return "publish";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> insertArticle(@RequestBody Article article, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            result.put("code",2);
            result.put("msg","用户未登录");
        }
        article.setCreatorId(user.getId());
        article.setArticleCreateTime(System.currentTimeMillis());
        article.setArticleUpdateTime(article.getArticleCreateTime());
        article.setLikeCount(0);
        article.setCommentCount(0);
        article.setReadingCount(0);
        int flag=articleService.insertArticle(article);
        if (1==flag){
            result.put("code",1);
            result.put("msg","话题发布成功");
        }else{
            result.put("code",0);
            result.put("msg","话题发布失败，请重试");
        }
        return result;
    }
}
