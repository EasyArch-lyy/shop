package redis.redis;

import java.util.Map;

public interface IRedis {

    boolean setp(String phone, String code);

    String getp(String phone);

    boolean nameAndPass(String username, String password);

    String getu(String username, String password);

    void setinformation(String phone, Map map);

    void getinformation(String phone);

    boolean judgephone(String phone);

    void savephoAndname(String phone, String username);

    boolean judgeName(String username);

    void nameAndPho(String username, String phone);

    String get(String username);

    void addPoint(String name, int kind, String position);
}
