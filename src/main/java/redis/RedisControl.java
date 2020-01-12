package redis;

import mybatis.MybatisControl;
import mybatis.User_course;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

/*
* Redis查询缓存类
* */
public class RedisControl {

    public RedisControl(){
    }

    //查询缓存是否买过课
//    @Test
    public boolean test1(String name,String course){
        Jedis jedis=RedisUtil.getJedis();
        System.out.println("连接成功");
        int i=0;
        //设置 redis 字符串数据
//        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        List<String> list3 = jedis.lrange(name,0,-1);
        for(String s:list3){
            if(course.equals(s)){
                System.out.println("买过该课");
                i=1;
                return true;
            }
        }
        if(i==0){
            System.out.println("未买过该课");
        }
        return false;
    }

    //同步存储到缓存
//    @Test
    public void synchronizationMysql () {
        SqlSession sqlSession = null;
        Jedis jedis = RedisUtil.getJedis();
        LinkedList<String> linkedList = new LinkedList<>();
        MybatisControl mybatisControl = new MybatisControl();
        System.out.println("连接成功");
        //创建姓名冗余列表
        List<String> namelist = new LinkedList<>();
        //获得数据表所有信息类型<User_course>
        List<User_course> list = mybatisControl.test10();
        //存储数据到缓存列表中
        for (int i = 0; i < list.size(); i++) {
            String n = list.get(i).getUsername();
            namelist.add(n);
            jedis.lpush(n, list.get(i).getCourse());
        }
        //姓名去重
        List<String> listt = namelist.stream().distinct().collect(Collectors.toList());
        // 获取存储的数据并输出
        for (String s : listt) {
            System.out.println(s);
            List<String> list2 = jedis.lrange(s,0,-1);
        }

    }
    //缓存更改回写
    public String redisToMysql(){
        String s=null;
        Jedis jedis=RedisUtil.getJedis();
        //            Set<String> set = jedis.keys("china:shandong*");
        //            for (String key : set) {
        //                System.out.println(key);
        //            }
        return s;
    }

//    public static void main(String[] args) {
//        RedisControl redisControl=new RedisControl();
//    }
}
