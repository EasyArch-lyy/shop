package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import pojo.Grade;

/*
* 用户成绩
* */

public interface UsergradeMapper {

    //添加某人的成绩记录（#{}表示数据库中数据）
    @Insert("insert into user_grade values(#{uid},#{cid},#{num},#{answer})")
    public void insertUserGrade(Grade grade);
    //更改用户成绩
//    @Update("update user_grade set num=#{num} where uid=#{uid}")
//    public void updateUserGrade(Grade grade);
    //删除某人的某门成绩记录
//    @Delete("delete from user_grade where uid=#{uid},course=#{cid}")
//    public void deleteStudent(String uid,String cid);
    //删除某人的所有课程成绩
    @Delete("delete from user_grade where uid=#{uid}")
    public void deleteAllUserGrade(Grade grade);
    //删除某门课程记录
    @Delete("delete from user_grade where cid=#{cid}")
    public void deleteGradeByCourse(Grade grade);
    //查询某人某门成绩
    @Select("select num from user_grade where uid=#{uid}&&cid=#{cid}")
    public int getGrade(Grade grade);
}

