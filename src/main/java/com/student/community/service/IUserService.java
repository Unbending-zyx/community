package com.student.community.service;

import com.student.community.vo.User;

public interface IUserService {
    /**
     * 插入用户方法
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据git用户的id更新user
     * @param user
     * @return
     */
    int updateUseByGitAccountId(User user);

    /**
     * 使用token查询
     * @param
     * @return
     */
    User selectUserByToken(String token);

    /**
     * 使用username查询user是否存在
     * @param user
     * @return
     */
    User selectUserByUserName(User user);

    /**
     * 通过id修改User
     * @param user
     * @return
     */
    int updateUseById(User user);

    int updateUseByUserName(User user);

    /**
     * 使用id查询用户
     * @param user
     * @return
     */
    User selectUserById(User user);

    /**
     * 根据id修改user的所有信息
     * @param user
     * @return
     */
    int updateUserAndPwdById(User user);
}
