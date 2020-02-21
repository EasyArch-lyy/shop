package dao.mapper;

import pojo.User_course;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import util.MyBatisUtil;

import java.util.List;

/**
  * Mybatis接口实现类增删改查类
  **/
public class MybatisControl {

    private static Logger logger=Logger.getLogger(MybatisDao.class);
    private SqlSession sqlSession=null;
    private UsercourseMapper usercourseMapper =null;
    private URLMapper urlMapper=null;

    public MybatisControl(){
        //SqlSession session = MyBatisUtil.getSqlSession();
        //UserDao userDao = session.getMapper(UserDao.class);
        sqlSession= MyBatisUtil.getSqlSession();
        usercourseMapper =sqlSession.getMapper(UsercourseMapper.class);
//        urlMapper=sqlSession.getMapper(URLMapper.class);
    }

   /**
    * 课程部分
    * */
    //查询指定用户是否选过该课
//   public static void main(String[] args) {
//       MybatisControl mybatisControl=new MybatisControl();
//       mybatisControl.isChoose();
//   }
   @Test
   public void isChoose(){
//       List<User_course> list=user_courseMapper.findUserAndCourse();
//       for(User_course a:list){
//           System.out.println(a.getUid());
//       }
       User_course user_course=new User_course("10","55");
       usercourseMapper.insertUserAndCourse(user_course);
       sqlSession.commit();
//       System.out.println(v);
//       if (v.equals("20")||v!=""){
//           System.out.println("11");
//       }
//       System.out.println("22");
   }
//   Exception in thread "main" org.apache.ibatis.binding.BindingException: Type interface dao.mapper.User_courseMapper is not known to the MapperRegistry.

//Exception in thread "main" org.apache.ibatis.exceptions.PersistenceException:
//### Error querying database.  Cause: org.apache.ibatis.binding.BindingException: Parameter 'uid' not found. Available parameters are [arg1, arg0, param1, param2]
//### Cause: org.apache.ibatis.binding.BindingException: Parameter 'uid' not found. Available parameters are [arg1, arg0, param1, param2]
   /**
    * @Param 用户id
    * @return List<String>
    * */
//    public List<String> SelectCourseByUser(String uid){
//        List<String>courses= usercourseMapper.getCourseByUsername(uid);
//        return ;
//    }

    //为某人新开通课程
    @Test
    public void addCourse(/*String uid,String cid*/){
        String uid="qwqw";String cid="dasfdf";
        logger.info("添加某人新课程");
//        usercourseMapper.insertUserAndCourse("uid","cid");
        sqlSession.commit();
    }

    //获得数据表所有信息写缓存
    @Test
    public void test10(){
        List<User_course>list= usercourseMapper.findUserAndCourse();
        for(User_course u:list){
            System.out.println(u.getUid()+"  "+u.getCid());
        }
//        return list;
//        return list;
    }
   /**
    * URL部分
    **/
   //添加url
   @Test
   public void insertURL(/*String url_name,String url*/){
       urlMapper.insertURL("url_name","url");
       sqlSession.commit();
   }
   //查询url
    public String selectURL(){
       String s=urlMapper.selectByURL("xxx");
       System.out.println(s);
       return s;
   }




}
