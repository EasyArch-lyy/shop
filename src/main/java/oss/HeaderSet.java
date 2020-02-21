package oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

/**
* 文件元信息
* */
public class HeaderSet {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAIkDQo325sxFQP";
    String accessKeySecret="sakkRrxINkJbe5wb3ZhA5TNXb8RSOk";
    String bucketName = "onlineclassshop";

    //用户试卷上传
    public void test1(String test_json) {

        String content = "Hello OSS";

        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header。
        ObjectMetadata meta = new ObjectMetadata();

        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(test_json.getBytes()));
        // 开启文件内容MD5校验。开启后OSS会把您提供的MD5与文件的MD5比较，不一致则抛出异常。
        meta.setContentMD5(md5);
        // 指定上传的内容类型。内容类型决定浏览器将以什么形式、什么编码读取文件。如果没有指定则根据文件的扩展名生成，如果没有扩展名则为默认值application/octet-stream。
        meta.setContentType("text/plain");
        // 设置内容被下载时的名称。
        meta.setContentDisposition("attachment; filename=\"MyTest\"");
        // 设置上传文件的长度。如超过此长度，则会被截断，为设置的长度。如不足，则为上传文件的实际长度。
        meta.setContentLength(test_json.length());
        // 设置内容被下载时网页的缓存行为。
        meta.setCacheControl("Download Action");
        // 设置缓存过期时间，格式是格林威治时间（GMT）。
        try {
            meta.setExpirationTime(DateUtil.parseIso8601Date("2020-1-31T15:00:00.000Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 设置内容被下载时的编码格式。
        meta.setContentEncoding("utf-8");
        // 设置header。
        meta.setHeader("<yourHeader>", "<yourHeaderValue>");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件。
        ossClient.putObject(bucketName, "", new ByteArrayInputStream(content.getBytes()), meta);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
