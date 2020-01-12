package mybatis;

public class User_grade {
    private String username;
    private String course;
    private int grade;

    public User_grade(String username, String course, int grade) {
        this.username = username;
        this.course = course;
        this.grade = grade;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
