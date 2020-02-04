package com.student.community.controller;

import com.student.community.dto.AccessTokenDTO;
import com.student.community.dto.GitUserDTO;
import com.student.community.mapper.UserMapper;
import com.student.community.modle.User;
import com.student.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitUserDTO gitUser = gitHubProvider.getGitUser(accessToken);
        if (gitUser!=null){
            //不为空  登陆成功 设置cookie和session
            User user = new User();
            user.setGitAccountId(String.valueOf(gitUser.getId()));
            user.setGitName(gitUser.getName());
            user.setGitBio(gitUser.getBio());
            user.setToken(UUID.randomUUID().toString());
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(user.getCreateTime());
            userMapper.insertUser(user);
            request.getSession().setAttribute("user",gitUser);
            return "redirect:/";
        }else{
            //为空  登录失败  返回登录页面
            return "login";
        }

    }


}
