package com.student.community.controller;

import com.github.pagehelper.PageHelper;
import com.student.community.dto.ArticleDTO;
import com.student.community.service.IArticleService;
import com.student.community.service.IUserService;
import com.student.community.utils.ArticleUtil;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ArticleUtil articleUtil;

    @GetMapping("/")
    public String getIndex(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.selectUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }

    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> articleList() {
        Map<String, Object> result = new HashMap<>();
        List<ArticleDTO> articleDTOList = articleService.selectAllArticleDTO();
        if (articleDTOList != null && articleDTOList.size() > 0) {
            result.put("code", 200);
            result.put("data", articleDTOList);
        }
        return result;
    }

    @RequestMapping(value = "/articleListQuary", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> articleListQuary(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(name = "pageSize", defaultValue = "12") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        int pageCount = articleService.selectArticleCount();
        pageCount=articleUtil.buildPageCount(pageCount,pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDTO> articleList = articleService.selectAllArticleDTO();
        if (articleList != null && articleList.size() > 0 && pageCount > 0) {
            result.put("pageCount", pageCount);
            result.put("pageNum", pageNum);
            result.put("code", 200);
            result.put("articleListQuary", articleList);
        }
        return result;
    }


}
