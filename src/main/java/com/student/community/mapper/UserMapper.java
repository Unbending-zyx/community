package com.student.community.mapper;

import com.student.community.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(gitAccountId,username,password,gitName,gitBio,accountName,token,createTime,updateTime)" +
            " VALUES(#{gitAccountId},#{username},#{password},#{gitName},#{gitBio},#{accountName},#{token},#{createTime},#{updateTime})")
    void insertUser(User user);
}
