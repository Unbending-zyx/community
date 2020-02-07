package com.student.community.utils;

import com.student.community.dao.IUserDAO;
import com.student.community.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {
    @Autowired
    private IUserDAO userDAO;

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
}
