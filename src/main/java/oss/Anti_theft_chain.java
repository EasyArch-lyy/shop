package oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.BucketReferer;

import java.util.ArrayList;
import java.util.List;
/**
* oss防盗链设置
* */
public class Anti_theft_chain {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIkDQo325sxFQP";
    String accessKeySecret="sakkRrxINkJbe5wb3ZhA5TNXb8RSOk";
    String bucketName = "onlineclassshop";

    public void test1() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        List<String> refererList = new ArrayList<String>();
// 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）。
        refererList.add("http://www.aliyun.com");
        refererList.add("http://www.*.com");
        refererList.add("http://www.?.aliyuncs.com");
        // 设置存储空间Referer列表。设为true表示Referer字段允许为空。
        BucketReferer br = new BucketReferer(true, refererList);
        ossClient.setBucketReferer(bucketName, br);

        ossClient.shutdown();
    }

//    设置防盗链
//    以下代码用于获取防盗链信息：
    public void test2() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取存储空间Referer白名单列表。
        BucketReferer br = ossClient.getBucketReferer(bucketName);
        List<String> refererList = br.getRefererList();
        for (String referer : refererList) {
            System.out.println(referer);
        }

// 关闭OSSClient。
        ossClient.shutdown();

    }


    //    清空防盗链
    public void test3() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 防盗链不能直接清空，需要新建一个允许空Referer的规则来覆盖之前的规则。
        BucketReferer br = new BucketReferer();
        ossClient.setBucketReferer(bucketName, br);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void main(String[] args) {
        Anti_theft_chain anti_theft_chain=new Anti_theft_chain();
        anti_theft_chain.test3();
    }
}
