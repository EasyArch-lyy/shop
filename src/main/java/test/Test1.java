package test;

import com.alibaba.fastjson.JSON;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Test1 {

    public void test1(){
        Jedis jedis = RedisUtil.getJedis();
//        ​User u1 = new User(UUID.randomUUID().toString(),"jack1",21,"m");
//        User u2 = new User(UUID.randomUUID().toString(),"jack2",22,"m");
//        User u3 = new User(UUID.randomUUID().toString(),"jack3",23,"m");
//        User u4 = new User(UUID.randomUUID().toString(),"jack4",24,"m");
//        User u5 = new User(UUID.randomUUID().toString(),"jack5",25,"m"); ​
//        Map<String,String> userMap = new HashMap<String,String>();
//        userMap.put("u1", JSON.toJSONString(u1));
//        userMap.put("u2",JSON.toJSONString(u2));
//        userMap.put("u3",JSON.toJSONString(u3));
//        userMap.put("u4",JSON.toJSONString(u4));
//        userMap.put("u5",JSON.toJSONString(u5));
//        ​ jedis.hmset("t_user",userMap);
    }

//    public static void main(String[] args) {
//        Test1 test1=new Test1();
//        test1.test1();
//    }
}
