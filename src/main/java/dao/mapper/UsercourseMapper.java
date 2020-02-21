package dao.mapper;

import org.apache.ibatis.annotations.*;
import pojo.User_course;

import java.util.List;
/*
* 用户课程dao
* */
public interface UsercourseMapper {
    //添加某人的课程记录（#{}表示数据库中数据）
    @Insert("insert into user_course(uid,cid)values(#{uid},#{cid})")
    public void insertUserAndCourse(User_course user_course);
//    更改用户课程
//    @Update("update user_course set cid=#{cid} where uid=#{uid}")
//    public void updateUser(User_course user_course);
    //删除某人的某门课程记录
    @Delete("delete from user_course where uid=#{uid}&&cid=#{cid}")
    public String deleteStudent(User_course user_course);
    //删除某人的所有课程
    @Delete("delete from user_courcse where uid=#{uid}")
    public void deleteCourseByUsername(User_course user_course);
    //删除某门课程
    @Delete("delete from user_course where cid=#{cid}")
    public void deleteUserByCourse(String cid);
    //查询某人的所有课程
    @Select("select cid from user_course where uid=#{uid}")
    public List<String> getCourseByUsername(User_course user_course);
    //查询全部数据表
    //column 数据库的列名;Property需要装配的属性名
    @Select("select * from user_course")
    @Results(
            {
                    @Result(column = "uid", property = "uid"),
                    @Result(column = "cid", property = "cid")
            }
    )
    public List<User_course> findUserAndCourse();

}
	
