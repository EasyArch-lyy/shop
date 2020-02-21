package pojo;
/*
* 用户类
* */
public class User {
    private String id;//姓名
    private String[] course;//已选课程
    private int classtime;//课时余额
    private int age;//年龄

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public int getClasstime() {
        return classtime;
    }

    public void setClasstime(int classtime) {
        this.classtime = classtime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
