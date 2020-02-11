package com.student.community.dao;

import com.student.community.vo.User;
import org.apache.ibatis.annotations.Param;


public interface IUserDAO {
    /**向数据库插入数据
     * @param user
     */
    void insertUser(User user);

    User selectUserBygitAccountId(User user);

    int updateUseByGitAccountId(User user);

    User selectUserByToken(@Param("token") String token);

    User selectUserByUserName(User user);

    int updateUseById(User user);

    int updateUseByUserName(User user);
}
