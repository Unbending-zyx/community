package com.student.community.controller;

import com.student.community.dto.ArticleDTO;
import com.student.community.exception.CustomizeException;
import com.student.community.service.IArticleService;
import com.student.community.utils.ArticleUtil;
import com.student.community.enums.StatusCode;
import com.student.community.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ArticleController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ArticleUtil articleUtil;

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView details(@PathVariable(name = "id") Integer id, ModelAndView modelAndView) {
        if (!articleUtil.isArticleExistsById(id)) {
            throw new CustomizeException(StatusCode.ARTICLE_IS_NOT_EXISTS);
        }
        //查找文章并返回
        ArticleDTO articleDTO = articleService.selectArticleDTOById(id);
        articleDTO.setReadingCount(articleDTO.getReadingCount() + 1);
        //修改其阅读数
        articleService.updateArticleReadingCountById(id);
        modelAndView.setViewName("article");
        modelAndView.addObject("articleDTO", articleDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/findRelatedArticles", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> findRelatedArticles(@RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "tag") String tag) {
        Map<String, Object> result = new HashMap<>();
        if (id == null || tag == null) {
            result.put("code", StatusCode.PARAM_ERROR.getType());
            result.put("msg", StatusCode.PARAM_ERROR.getDesc());
            return result;
        }
        List<Article> articleList = articleService.selectArticleByTags(id, tag);
        result.put("code", StatusCode.SUCCESS.getType());
        result.put("msg", StatusCode.SUCCESS.getDesc());
        result.put("relatedArticles", articleList);
        return result;
    }

}
