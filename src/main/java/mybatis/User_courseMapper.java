package mybatis;

import org.apache.ibatis.annotations.*;
import org.junit.Test;

import java.util.List;

public interface User_courseMapper {
    //添加某人的课程记录
    @Insert("insert into user_course values(#{username},#{course})")
    public void insertUserAndCourse(User_course user_course);

    @Update("update user_course set course=#{course} where username=#{username}")
    public String updateUser(User_course user_course);

    //删除某人的某门课程记录
    @Delete("delete from user_course where username=#{username},course=#{course}")
    public String deleteStudent(String username,String course);

    //删除某人的所有课程
    @Delete("delete from user_courcse where username=#{username}")
    public void deleteCourseByUsername(String username);
    //删除某门课程
    @Delete("delete from user_course where course=#{course}")
    public void deleteUserByCourse(String course);
    //查询某人的所有课程
    @Select("select course from user_course where username=#{username}")
    public List<String> getCourseByUsername(String username);

    //查询全部数据表
    @Select("select * from user_course")
    @Results(
            {
                    @Result(column = "username", property = "username"),
                    @Result(column = "course", property = "course")
            }
    )
    public List<User_course> findUserAndCourse();

}
	
