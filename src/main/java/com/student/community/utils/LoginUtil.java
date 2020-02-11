package com.student.community.utils;

import com.student.community.dao.IUserDAO;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {
    @Autowired
    private IUserDAO userDAO;

    /**
     * 判断git用户是否存在
     * @param user
     * @return
     */
    public boolean isGitUserRecord(User user){
        User u=userDAO.selectUserBygitAccountId(user);
        if (u!=null){
            if (u.getGitAccountId()!=null && u.getGitAccountId().length()>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    /**
     * 注册时  用于判断用户是否已经存在的方法
     * @param user
     * @return
     */
    public boolean isUserByUserName(User user){
        User u=userDAO.selectUserByUserName(user);
        if (u!=null){
            return true;
        }
        return false;
    }
}
