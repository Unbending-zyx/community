package com.student.community.controller;

import com.student.community.dto.ArticleDTO;
import com.student.community.exception.CustomizeException;
import com.student.community.service.IArticleService;
import com.student.community.utils.ArticleUtil;
import com.student.community.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ArticleUtil articleUtil;

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView details(@PathVariable(name = "id") Integer id, ModelAndView modelAndView) {
        if (!articleUtil.isArticleExistsById(id)){
            throw new CustomizeException(StatusCode.ARTICLE_IS_NOT_EXISTS);
        }
        //查找文章并返回
        ArticleDTO articleDTO = articleService.selectArticleDTOById(id);
        articleDTO.setReadingCount(articleDTO.getReadingCount()+1);
        //修改其阅读数
        articleService.updateArticleReadingCountById(id);
        modelAndView.setViewName("article");
        modelAndView.addObject("articleDTO", articleDTO);
        return modelAndView;
    }
}
