package com.student.community.controller;

import com.student.community.cache.TagCache;
import com.student.community.dto.TagDTO;
import com.student.community.service.IArticleService;
import com.student.community.utils.ArticleUtil;
import com.student.community.vo.Article;
import com.student.community.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/publish")
@Controller
public class PublishController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ArticleUtil articleUtil;

    @GetMapping("/show")
    public String publish() {
        return "publish";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView articleDetails(@PathVariable("id") int id,
                                       ModelAndView modelAndView,
                                       HttpServletRequest request) {
        modelAndView.setViewName("publish");
        Article article = articleService.selectArticleById(id);
        if (article == null) {
            modelAndView.addObject("code", 404);
            modelAndView.addObject("msg", "文章不存在");
            return modelAndView;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (articleUtil.isArticleUserCreated(article, user)) {
            modelAndView.addObject("code", 200);
            modelAndView.addObject("msg", "成功");
            modelAndView.addObject("article", article);
            return modelAndView;
        } else {
            modelAndView.addObject("code", 400);
            modelAndView.addObject("msg", "没有权限");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> updateArticle(@RequestBody Article article,
                                      HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("code", 2);
            result.put("msg", "用户未登录");
        }
        article.setArticleUpdateTime(System.currentTimeMillis());
        int flag = articleService.updateArticleById(article);
        if (1 == flag) {
            result.put("code", 1);
            result.put("msg", "话题更新成功");
        } else {
            result.put("code", 0);
            result.put("msg", "话题更新失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> insertArticle(@RequestBody Article article, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("code", 2);
            result.put("msg", "用户未登录");
        }

        String invalid = TagCache.filterInvalid(article.getTag());
        if (StringUtils.isNotBlank(invalid)){
            result.put("code", 3);
            result.put("msg", "输入非法标签:"+invalid);
            return result;
        }

        article.setCreatorId(user.getId());
        article.setArticleCreateTime(System.currentTimeMillis());
        article.setArticleUpdateTime(article.getArticleCreateTime());
        article.setLikeCount(0);
        article.setCommentCount(0);
        article.setReadingCount(0);
        int flag = articleService.insertArticle(article);
        if (1 == flag) {
            result.put("code", 1);
            result.put("msg", "话题发布成功");
        } else {
            result.put("code", 0);
            result.put("msg", "话题发布失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTags() {
        Map<String, Object> result = new HashMap<>();
        List<TagDTO> tagDTOS=TagCache.getTags();
        result.put("allTags",tagDTOS);
        return result;
    }


}
