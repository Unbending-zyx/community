package com.student.community.controller;

import com.student.community.dto.AccessTokenDTO;
import com.student.community.dto.GitUserDTO;
import com.student.community.utils.LoginUtil;
import com.student.community.utils.UserUtil;
import com.student.community.vo.User;
import com.student.community.provider.GitHubProvider;
import com.student.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    private LoginUtil loginUtil;
    @Autowired
    private UserUtil userUtil;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitUserDTO gitUser = gitHubProvider.getGitUser(accessToken);
        if (gitUser!=null && gitUser.getId()!=null){
            //不为空  登陆成功 设置cookie和session
            User user = new User();
            user.setGitAccountId(String.valueOf(gitUser.getId()));
            user.setGitName(gitUser.getName());
            user.setGitBio(gitUser.getBio());
            user.setAvatarUrl(gitUser.getAvatar_url());
            User u=userUtil.setToken(user);
            //判断该条记录是否已经存在于数据库
            if (!loginUtil.isGitUserRecord(u)){
                userService.insertUser(u);
            }else{
                //如果该git用户已经存在  则只进行token的更新
                userService.updateUseByGitAccountId(u);
            }
            response.addCookie(new Cookie("token",u.getToken()));
            return "redirect:/";
        }else{
            //为空  登录失败  返回登录页面
            return "login";
        }

    }


}
