package com.student.community.service.impl;

import com.student.community.dao.IUserDAO;
import com.student.community.vo.User;
import com.student.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public int updateUseByGitAccountId(User user) {
        return userDAO.updateUseByGitAccountId(user);
    }

    @Override
    public User selectUserByToken(String token) {
        return userDAO.selectUserByToken(token);
    }

    @Override
    public User selectUserByUserName(User user) {
        return userDAO.selectUserByUserName(user);
    }

    @Override
    public int updateUseById(User user) {
        return userDAO.updateUseById(user);
    }

    @Override
    public int updateUseByUserName(User user) {
        return userDAO.updateUseByUserName(user);
    }
}
