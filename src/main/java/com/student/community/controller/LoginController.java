package com.student.community.controller;

import com.student.community.service.IUserService;
import com.student.community.utils.LoginUtil;
import com.student.community.utils.UserUtil;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private LoginUtil loginUtil;
    @Autowired
    private UserUtil userUtil;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> insertUser(@RequestBody User user, HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        User u = userUtil.setToken(user);
        //判断username是否已经存在
        if (loginUtil.isUserByUserName(u)) {
            result.put("type", "1");
            result.put("msg", "该用户已存在");
            return result;
        }
        userService.insertUser(u);
        result.put("type", "0");
        result.put("msg", "注册成功");
        return result;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> login(@RequestBody User user,
                              HttpServletResponse response,
                              HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User u = userService.selectUserByUserName(user);
        if (u != null) {
            if (!user.getPassword().equals(u.getPassword())) {
                result.put("code", 2);
                result.put("msg", "密码错误");
                return result;
            }
            User userToken = userUtil.setToken(u);
            userService.updateUseByUserName(userToken);
            Cookie cookie = new Cookie("token", userToken.getToken());
            cookie.setPath("/");
            response.addCookie(cookie);
            request.getSession().setAttribute("user",userToken);
            result.put("code", 0);
            result.put("msg", "登录成功");
            return result;
        } else {
            result.put("code", 1);
            result.put("msg", "用户名不存在");
            return result;
        }
    }

}
