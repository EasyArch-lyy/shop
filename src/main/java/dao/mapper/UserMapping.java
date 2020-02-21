package dao.mapper;

import org.apache.ibatis.annotations.*;
import pojo.User;

import java.util.Map;

/*
* 用户信息dao
* */

public interface UserMapping {
    //新增用户
    @Insert("insert into user_message(uid,classtime,age) values(#{uid},#{classtime},#{age})")
    public void insertUser(User user);
    @Select("select classtime from user_message where uid=#{uid}")
    public int getClassTime(User user);
    //更改用户课时
    @Update("update user_message set classtime=#{classtime} where uid=#{uid}")
    public String updateClassTime(User user);
    //更改用户年龄
    @Update("update user_message set age=#{age} where uid=#{uid}")
    public void updateAge(User user);

}