package redis.redis;

//import netty.mybatis.Mymybatis;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Map;

public enum  Redis implements IRedis{

    jediss;
    private Jedis jedis;
    Redis(){
         jedis = new Jedis("localhost");
    }

    //缓存验证码
    @Override
    public  boolean setp(String phone, String code){
         if(jedis.set(phone,code).equals("OK")){
             //设置缓存时间
             jedis.expire(phone, 120);
             return true;
         }
        return false;
    }

    @Override
    public  String getp(String phone){
        if(jedis.get(phone)!=null){
            return jedis.get(phone);
        }
        return "验证码错误或过期!!!";

    }

    //验证帐号密码
    @Override
    public  boolean nameAndPass(String username, String password){
        if(jedis.set(username,password).equals("OK")){
            return true;
        }
        return false;
    }

    @Override
    public  String getu(String username, String password){
        if(jedis.get(username).equals(password)){
            return "ok";
        }
        return "密码错误!!!";
    }

    @Override
    public void setinformation(String phone, Map map){
        jedis.hmset(phone,map);
        jedis.expire(phone,604800);
    }

    @Override
    public void getinformation(String Bphone){
        Map map=jedis.hgetAll(Bphone);
        if(map==null||map.size()==0){
            //ICeate iCeate=new Create();
            //map=iCeate.uselectAll("user",Bphone);
//            try {
//                map=new Mymybatis().selset(Bphone);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if(map!=null&&map.size()!=0){
//                setinformation(Bphone,map);
//            }else {
//                map.put("rs","没有完善信息");
//            }
        }

//    return map;

    }

    @Override
    public boolean judgephone(String phone) {
        if(jedis.get(phone)!=null){
            return false;
        }
        return true;
    }

    @Override
    public void savephoAndname(String phone,String username) {
        jedis.set(phone,username);
    }

    @Override
    public boolean judgeName(String username) {
        if(jedis.get(username)!=null){
            return false;
        }
        return true;
    }

    @Override
    public void nameAndPho(String username,String phone) {
        jedis.set(username,phone);
    }

    @Override
    public String get(String username){
        return jedis.get(username);
    }

    @Override
    public void addPoint(String name, int kind, String position) {
//        jedis.set();
    }


}
