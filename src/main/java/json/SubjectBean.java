package json;
/*
* 课程bean
* */
public class SubjectBean {
    private int id;//课程编号
    private String kind;//课程类型
    private String time;//课时
    private String age;//适合年龄
    private String purl;//图片url
    private String vurl;//视频url
    private String turl;//超链接url

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubjectBean(int id, String kind, String time, String age, String purl, String vurl, String turl) {
        this.id = id;
        this.kind = kind;
        this.time = time;
        this.age = age;
        this.purl = purl;
        this.vurl = vurl;
        this.turl = turl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

//    public PaperJSON getObj(String kind){
//
//    }
}
