package netty.SyncHttpResponse;

public class SyncHttpResponse {
    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient("https://onlineclassshop.oss-cn-beijing.aliyuncs.com/abc123.txt");
        client.connect();
        System.out.println(client.getBody());
    }
}
