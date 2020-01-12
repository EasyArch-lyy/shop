package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.test2.User;
import util.MyBatisUtil;

import java.util.List;

public class User_courseTest {
    private static Logger logger=Logger.getLogger(User_courseTest.class);
    private SqlSession sqlSession=null;
    private User_courseMapper user_courseMapper=null;
    /**
     * 测试方法前调用
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        sqlSession= MyBatisUtil.getSqlSession();
        user_courseMapper=sqlSession.getMapper(User_courseMapper.class);
    }

    /**
     * 测试方法后调用
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        logger.info("添加学生");
        User_course user_course=new User_course("qqq","rrda");
        user_courseMapper.insertUserAndCourse(user_course);
        sqlSession.commit();
    }

    @Test
    public void testUpdate() {
        logger.info("更新学生");
        User_course user_course=new User_course("琪琪2","ss");
        user_courseMapper.updateUser(user_course);
        sqlSession.commit();
    }

    @Test
    public void testDelete() {
        logger.info("删除学生");
        user_courseMapper.deleteUserByCourse("xxx");
        sqlSession.commit();
    }

//    @Test
//    public void testGetById() {
//        logger.info("成员通过名字查找");
//        User_course student=user_courseMapper.getStudentById("xxx");
//        System.out.println(student);
//    }

//    @Test
//    public void testFindStudents() {
//        logger.info("");
//        List<User_course> studentList=user_courseMapper.findStudents();
//        for(User_course student:studentList){
//            System.out.println(student);
//        }
//    }
    @Test
    public void test10(){
        List<User_course>list=user_courseMapper.findUserAndCourse();
        for(User_course u:list){
            System.out.println(u.getUsername()+"  "+u.getCourse());
        }
    }
}
