package mybatis;

public class User_grade {
    private String username;
    private String course;
    private int grade;
    private String answer;

    public User_grade(String username, String course, int grade, String answer) {
        this.username = username;
        this.course = course;
        this.grade = grade;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
