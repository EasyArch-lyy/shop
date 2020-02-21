package dao.mapper;

import json.Tojson;
import pojo.Grade;
import pojo.URLObject;
import pojo.User;
import pojo.User_course;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import util.MyBatisUtil;

import java.util.List;

public class MybatisDao {
    private static Logger logger=Logger.getLogger(MybatisDao.class);
    private SqlSession sqlSession=null;
    private URLMapper urlMapper=null;
    private UsercourseMapper usercourseMapper =null;
    private UserMapping userMapping=null;
    private UsergradeMapper usergradeMapper=null;

    /**
     * 测试方法前调用
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        sqlSession= MyBatisUtil.getSqlSession();
        usercourseMapper =sqlSession.getMapper(UsercourseMapper.class);
        urlMapper=sqlSession.getMapper(URLMapper.class);
        userMapping=sqlSession.getMapper(UserMapping.class);
        usergradeMapper=sqlSession.getMapper(UsergradeMapper.class);
    }

    /**
     * 测试方法后调用
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }

/*
* 课程-用户dao
* */
    //增加用户课程
    public void userInsertcourse(String uid,String cid) {
        User_course user_course=new User_course(uid,cid);
        logger.info(uid+" 课程添加: "+cid);
        usercourseMapper.insertUserAndCourse(user_course);
        sqlSession.commit();
    }

//    @Test
//    public void testUpdate() {
//        logger.info("更新学生");
//        User_course user_course=new User_course("琪琪2","ss");
//        usercourseMapper.updateUser(user_course);
//        sqlSession.commit();
//    }

    //关闭课程清空记录
    public void courseDelete(User_course user_course) {
        logger.info("删除某课程");
        usercourseMapper.deleteUserByCourse(user_course.getCid());
        sqlSession.commit();
    }

    //删除用户的某门课程记录
    public void deleteStudent(User_course user_course){
        logger.info("删除该用户所有课程");
        usercourseMapper.deleteStudent(user_course);
    }

    //判断是否选过该课
    public boolean isChoose(String uid,String cid){
        User_course course=new User_course(uid);
        List<String>list=usercourseMapper.getCourseByUsername(course);
        for (String s:list){
            if(s.equals(cid)){
                return true;
            }
        }
        return false;
    }

    public List<String> getf(String uid){
        User_course course=new User_course(uid);
        List<String>list=usercourseMapper.getCourseByUsername(course);
        for (String s:list){
            System.out.println(s);
        }
        return list;
    }
/*
* user  dao
* */

    //新增用户
    public void insertUser(User user){
        userMapping.insertUser(user);
    }
    //更改用户课时
    public String updateClassTime(String uid,int time/*需要扣除的课时*/){
        User user=new User();
        user.setId(uid);
        int a=userMapping.getClassTime(user);
        if(a>time){
            user.setClasstime(a-time);
            userMapping.updateClassTime(user);
            return Tojson.getSingleton().to("课时扣除成功");
        }else {
            return Tojson.getSingleton().to("用户课时不足");
        }
    }
    //用户年龄更改
    public void updateAge(String uid,int age){
        User user=new User();
        user.setId(uid);
        user.setAge(age);
        userMapping.updateAge(user);
    }

/*
* URL  dao类
* */
    //增加url
    public void insertURL(String objectName,String url){
        URLObject urlObject=new URLObject();
        urlObject.setObjectName(objectName);
        urlObject.setUrl(url);
        urlMapper.insertURL(urlObject);
    }
    //查询实体对应的URL
    public String getURL(String object){
        URLObject urlObject=new URLObject(object);
        urlObject.setObjectName(object);
        return urlMapper.selectByURL(urlObject);
    }
/*
* 用户和成绩dao
* */
    //添加某人的成绩记录（#{}表示数据库中数据）
    public void insertGrade(Grade grade){
        usergradeMapper.insertUserGrade(grade);
    }
    //查询某人某门成绩
    public int getGrade(Grade grade){
        return usergradeMapper.getGrade(grade);
    }

}
