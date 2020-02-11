package com.student.community.utils;

import com.student.community.vo.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUtil {
    /**
     * 设置user的token和创建时间及更新时间
     * @param user
     * @return
     */
    public User setToken(User user){
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        if(user.getCreateTime()==null){
            user.setCreateTime(System.currentTimeMillis());
        }
        if (user.getUpdateTime()!=null){
            user.setUpdateTime(System.currentTimeMillis());
        }else{
            user.setUpdateTime(user.getCreateTime());
        }
        return user;
    }
}
