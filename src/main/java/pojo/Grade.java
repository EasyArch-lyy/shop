package pojo;
/*
* 成绩类
* */
public class Grade {
    private String uid;//用户id
    private String pid;//试卷id
    private int num;   //得分
    private String answer;//用户答案记录

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Grade(String uid, String pid, int num, String answer) {
        this.uid = uid;
        this.pid = pid;
        this.num = num;
        this.answer = answer;
    }
    public Grade(){

    };

}
