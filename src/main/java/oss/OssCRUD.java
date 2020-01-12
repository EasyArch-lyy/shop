package oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;

import java.io.File;
import java.io.IOException;

/**
* OSS增删改查
* */
public class OssCRUD {
    private  String endpoint;       //连接区域地址
    private  String accessKeyId;    //连接keyId
    private  String accessKeySecret;    //连接秘钥
    private  String bucketName;     //需要存储的bucketName
    private  String picLocation;    //图片保存路径

    public OssCRUD() {
        try {
            this.endpoint = SystemConfig.getConfigResource("endpoint");
            this.bucketName = SystemConfig.getConfigResource("bucketName");
//            this.picLocation = SystemConfig.getConfigResource("picLocation");
            this.accessKeyId = SystemConfig.getConfigResource("accessKeyId");
            this.accessKeySecret = SystemConfig.getConfigResource("accessKeySecret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * oss配置读取
     *
     * */
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    public String getAccessKeySecret() {
        return accessKeySecret;
    }
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getPicLocation() {
        return picLocation;
    }

    //    public void setPicLocation(String picLocation) {
//        this.picLocation = picLocation;
//    }
    /*
     * oss下载部分
     */
    public String download(String objectName,String localFile) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIkDQo325sxFQP";
        String accessKeySecret = "sakkRrxINkJbe5wb3ZhA5TNXb8RSOk";
        String bucketName = "10056";
        String jsonString = null;
        File file =new File(localFile);
        //String objectName = "<yourObjectName>";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), file);

        ossClient.shutdown();
//        jsonString = FileUtils.readFileToString(file);
        return jsonString;
    }
    /*
    * 上传部分
    * */
    public void upload( String localFile,String nameInbucket){
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIkDQo325sxFQP";
        String accessKeySecret = "sakkRrxINkJbe5wb3ZhA5TNXb8RSOk";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject("onlineclassshop", nameInbucket, new File(localFile));

// 关闭OSSClient。
        ossClient.shutdown();
    }


    public static void main(String[] args) throws IOException {
        OssCRUD ossConfigure=new OssCRUD();
        System.out.println(ossConfigure.accessKeyId);
        System.out.println("oss连接成功");
//        ossConfigure.download("KdniaoTrackQueryAPI","/home/lyy/下载/sdf");//下载
        ossConfigure.upload("/home/lyy/桌面/abc123","abc123.txt");//上传
    }
}

