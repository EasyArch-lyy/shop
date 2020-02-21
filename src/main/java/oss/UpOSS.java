package oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSS.*;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpOSS {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIkDQo325sxFQP";
    String accessKeySecret="sakkRrxINkJbe5wb3ZhA5TNXb8RSOk";
    Long lenght= 0L;

//    流式上传
//    使用ossClient.putObject上传数据流到OSS。上传字符串
    public void test1(String content,String key) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
//         content = "Hello OSS";
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest("10056", key, new ByteArrayInputStream(content.getBytes()));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传字符串。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

//    上传Byte数组
//    以下代码用于上传Byte数组：
    public void test2() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传Byte数组。
        byte[] content = "/home/lyy/upvideo/1.mp4".getBytes();
        ossClient.putObject("onlineclassshop", "2.mp4", new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //    上传网络流
//    以下代码用于上传网络流：
    public void test3() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL("https://www.aliyun.com/").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject("onlineclassshop", "ww.txt", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    //    上传文件流
//    以下代码用于上传文件流：
    public void test4() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println("实例创建");
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/home/lyy/uptxt/中小学python.json");
            System.out.println("获取文件");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject("10056", "中小学python.json", inputStream);
        //aliyun-abc.oss-cn-hangzhou.aliyuncs.com/abc/myphoto.jpg
        //https://onlineclassshop.oss-cn-beijing.aliyuncs.com/1.mp4
        System.out.println("上传开始");
        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传完成");

    }
    //输出Bucket
    public void test5(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }
    //简单直传
    public void test6(String localFile,String nameInbucket){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("application/json");
        ossClient.putObject("10056", nameInbucket, new File(localFile),meta);
        System.out.println("上传完成");
        ossClient.shutdown();
    }

    /**
     * 判断文件是否存在
     * @param remoteFileName
     * @return result
     */
    public Boolean doesObjectExist(String remoteFileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean result=ossClient.doesObjectExist("10056",remoteFileName);
        ossClient.shutdown();
        System.out.println(result);
        return result;
    }
//    追加上传
    public Long test8(String yourObjectName,Long Position,String content){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("text/plain");
// 通过AppendObjectRequest设置多个参数。
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest("10056", yourObjectName, new ByteArrayInputStream(content.getBytes()),meta);
// 通过AppendObjectRequest设置单个参数。
// 设置存储空间名称。
//appendObjectRequest.setBucketName("<yourBucketName>");
// 设置文件名称。
//appendObjectRequest.setKey("<yourObjectName>");
// 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为InputStream类型。
//appendObjectRequest.setInputStream(new ByteArrayInputStream(content1.getBytes()));
// 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为File类型。
//appendObjectRequest.setFile(new File("<yourLocalFile>"));
// 指定文件的元信息，第一次追加时有效。
//appendObjectRequest.setMetadata(meta);

// 第一次追加。
// 设置文件的追加位置。
        appendObjectRequest.setPosition(Position);
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
//        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
// 文件的64位CRC值。此值根据ECMA-182标准计算得出。
//        System.out.println(appendObjectResult.getObjectCRC());

// 第二次追加。
// nextPosition指明下一次请求中应当提供的Position，即文件当前的长度。
        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
        Long a=appendObjectResult.getNextPosition();
//        System.out.println(a);
//        appendObjectRequest.setInputStream(new ByteArrayInputStream(content2.getBytes()));
//        appendObjectResult = ossClient.appendObject(appendObjectRequest);

// 第三次追加。
//        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
//        appendObjectRequest.setInputStream(new ByteArrayInputStream(content3.getBytes()));
//        appendObjectResult = ossClient.appendObject(appendObjectRequest);

// 关闭OSSClient。
        ossClient.shutdown();
        return a;
    }

    public static void main(String[] args) {
        UpOSS upOSS=new UpOSS();
        System.out.println("初始长度："+upOSS.lenght);
        for(int i=0;i<2;i++){
            upOSS.lenght = upOSS.test8("testWord0", upOSS.lenght,"wowowowowowowo\n");
            System.out.println("上传后长度："+upOSS.lenght);
        }

    }
}
