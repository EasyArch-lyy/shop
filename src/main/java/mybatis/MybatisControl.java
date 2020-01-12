package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import util.MyBatisUtil;

import java.util.List;

/**
* 是否选过课增删改查类
* */
public class MybatisControl {

    private static Logger logger=Logger.getLogger(User_courseTest.class);
    private SqlSession sqlSession=null;
    private User_courseMapper user_courseMapper=null;

    public MybatisControl(){
        sqlSession= MyBatisUtil.getSqlSession();
        user_courseMapper=sqlSession.getMapper(User_courseMapper.class);
    }
    //查询指定用户课程
    public List<String> test(){
        List<String>courses=user_courseMapper.getCourseByUsername("");
        return courses;
    }
    //判断该用户是否有该课
    public boolean test1(String username,String course){
        List<String>courses=test();
        for(String a:courses){
            if(course.equals(a)){
                System.out.println("选过该课");
                return true;
            }
        }
        System.out.println("为购买该课程");
        return false;
    }
    //为某人新开通课程
    @Test
    public void test2(){
        logger.info("添加某人新课程");
        User_course user_course=new User_course("eee","ll");
        user_courseMapper.insertUserAndCourse(user_course);
        sqlSession.commit();
    }
    //@Test
    //    public void testInsert() {
    //        logger.info("添加学生");
    //        User_course user_course=new User_course("qqq","rrda");
    //        user_courseMapper.insertUserAndCourse(user_course);
    //        sqlSession.commit();
    //    }


    //获得数据表所有信息写缓存
    @Test
    public List<User_course> test10(){
        List<User_course>list=user_courseMapper.findUserAndCourse();
        for(User_course u:list){
            System.out.println(u.getUsername()+"  "+u.getCourse());
        }
        return list;
    }

}
