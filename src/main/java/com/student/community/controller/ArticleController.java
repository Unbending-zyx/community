package com.student.community.controller;

import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
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

    @RequestMapping(value = "/article/{id}",method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView details(@PathVariable(name = "id") Integer id,ModelAndView modelAndView){
        ArticleDTO articleDTO=articleService.selectArticleDTOById(id);
        modelAndView.setViewName("article");
        modelAndView.addObject("articleDTO",articleDTO);
        return modelAndView;
    }
}
