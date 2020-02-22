package com.student.community.controller;

import com.github.pagehelper.PageHelper;
import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
import com.student.community.utils.ArticleUtil;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ArticleUtil articleUtil;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        return "message";
    }

    @RequestMapping(value="/articleListQuary",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> selectArticleById(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "12") int pageSize,
                                          HttpServletRequest request) {
        Map<String,Object> result=new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");

        int pageCount = articleService.selectArticleCountByUserId(user.getId());
        pageCount=articleUtil.buildPageCount(pageCount,pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleList=articleService.selectAllArticleDTOByUserId(user);
        if (articleList != null && articleList.size() > 0 && pageCount > 0) {
            result.put("pageCount", pageCount);
            result.put("pageNum", pageNum);
            result.put("code", 200);
            result.put("articleListQuary", articleList);
        }

        return result;
    }


}
