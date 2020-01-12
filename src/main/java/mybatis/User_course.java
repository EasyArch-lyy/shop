package mybatis;

public class User_course {
    private String username;
    private String course;

    public User_course(String username, String course) {
        this.username = username;
        this.course = course;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
