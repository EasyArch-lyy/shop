package oss;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 上传回调
 */
public class CallbackSample {

    private  String endpoint ;
    private  String accessKeyId ;
    private  String accessKeySecret ;
    private  String bucketName ;

    // The callback url，for example: http://oss-demo.aliyuncs.com:23450或http://0.0.0.0:9090
    // 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://127.0.0.1:9090。
    private static final String callbackUrl = "http://127.0.0.1:8080";

    public CallbackSample(){
        try {
            this.endpoint = SystemConfig.getConfigResource("endpoint");
            this.bucketName = SystemConfig.getConfigResource("bucketName");
            this.accessKeyId = SystemConfig.getConfigResource("accessKeyId");
            this.accessKeySecret = SystemConfig.getConfigResource("accessKeySecret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //上传回调
    public void test1() throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String content = "Hello OSS";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "key",
                    new ByteArrayInputStream(content.getBytes()));
            // 上传回调参数
            Callback callback = new Callback();
            callback.setCallbackUrl(callbackUrl);
            // 设置回调请求消息头中Host的值，如oss-cn-hangzhou.aliyuncs.com
            callback.setCallbackHost("oss-cn-beijing.aliyuncs.com");
            callback.setCallbackBody("{\n" +
                    "            \"callbackUrl\":\"127.0.0.1/8080\",\n" +
                    "            \"callbackHost\":\"oss-cn-beijing.aliyuncs.com\",\n" +
                    "            \"callbackBody\":\"{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}\",\n" +
                    "            \"callbackBodyType\":\"application/json\"\n" +
                    "            }");
            // 设置发起回调时请求body的值
            //JSON 字段示例如下：
//            {
//            "callbackUrl":"121.101.166.30/test.php",
//            "callbackHost":"oss-cn-hangzhou.aliyuncs.com",
//            "callbackBody":"{\"mimeType\":${mimeType},\"size\":${size}}",
//            "callbackBodyType":"application/json"
//            }

            //{
            //"callbackUrl":"121.43.113.8:23456/index.html",
            //"callbackBody":"bucket=${bucket}
            // &object=${object}
            // &etag=${etag}
            // &size=${size}
            // &mimeType=${mimeType}
            // &imageInfo.height=${imageInfo.height}
            // &imageInfo.width=${imageInfo.width}
            // &imageInfo.format=${imageInfo.format}
            // &my_var=${x:my_var}"
            //}
//            callback.setCallbackBody(
//                    "{\\\"bucket\\\":/*${bucket},*/" +bucketName+
//                     "\\\"object\\\":/*${object},*/" ++
//                     "\\\"mimeType\\\":${mimeType}," +
//                     "\\\"size\\\":${size}," +
//                     "\\\"my_var1\\\":${x:var1}," +
//                     "\\\"my_var2\\\":${x:var2}}");
            // 设置发起回调请求的Content-Type
            callback.setCalbackBodyType(CalbackBodyType.JSON);
            // 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始
//            callback.addCallbackVar("x:var1", "value1");
//            callback.addCallbackVar("x:var2", "value2");
            //开始简单上传
            putObjectRequest.setCallback(callback);

            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            // 读取上传回调返回的消息内容
            byte[] buffer = new byte[1024];
            putObjectResult.getResponse().getContent().read(buffer);
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作
            putObjectResult.getResponse().getContent().close();

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }

    public static void main(String[] args) throws IOException {

        CallbackSample callbackSample=new CallbackSample();
        callbackSample.test1();
    }
}
