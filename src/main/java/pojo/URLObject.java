package pojo;

public class URLObject {
    private String ObjectName;
    private String url;

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public URLObject(String objectName) {
        ObjectName = objectName;
    }

    public URLObject(String objectName, String url) {
        ObjectName = objectName;
        this.url = url;
    }
    public URLObject(){

    }
}
