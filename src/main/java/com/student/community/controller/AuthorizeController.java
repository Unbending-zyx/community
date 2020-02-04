package com.student.community.controller;

import com.student.community.pojo.AccessTokenPOJO;
import com.student.community.pojo.GitUserPOJO;
import com.student.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenPOJO accessTokenPOJO = new AccessTokenPOJO();
        accessTokenPOJO.setClient_id(clientId);
        accessTokenPOJO.setClient_secret(clientSecret);
        accessTokenPOJO.setCode(code);
        accessTokenPOJO.setState(state);
        accessTokenPOJO.setRedirect_uri(redirectUri);

        String accessToken = gitHubProvider.getAccessToken(accessTokenPOJO);
        GitUserPOJO gitUser = gitHubProvider.getGitUser(accessToken);
        System.out.println(gitUser.getName());

        return "index";
    }


}
