package pojo;
/*
* 用户与课程关联类
* */
public class User_course {
    private String uid;//用户id
    private String cid;//课程id

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public User_course(String uid, String cid) {
        this.uid = uid;
        this.cid = cid;
    }

    public User_course(String uid) {
        this.uid = uid;
    }

}
