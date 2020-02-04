package com.student.community.provider;

import com.alibaba.fastjson.JSON;
import com.student.community.dto.AccessTokenDTO;
import com.student.community.dto.GitUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    /**
     * 使用post请求访问github 获取access_token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String accessToken = string.split("&")[0].split("=")[1];
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据获取的access_token 获取用户信息
     * @param accessToken
     * @return
     */
    public GitUserDTO getGitUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitUserDTO gitUserDTO = JSON.parseObject(string, GitUserDTO.class);
            return gitUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
