package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.log4j.Logger;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_LENGTH;

public class Handler extends ChannelInboundHandlerAdapter {
    private Logger logger = Logger.getLogger(Handler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String json = "";
        logger.info("===================进入netty channelRead了=======================");
        if (msg instanceof FullHttpRequest) {
            logger.info("进入http请求了");
            json = handleHttpRequest(ctx, (FullHttpRequest) msg);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(json.getBytes(StandardCharsets.UTF_8)));
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
            ctx.writeAndFlush(response);
        }
        logger.info("最后返回数据" + json);
    }

    /**
     * 处理业务流程
     */
    private String handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest fuHr) {
        //上传回调URL
        String updateURL = null;

//        String url=handshaker.uri().split("/")[3];
//		System.out.println(url);
//		String request = ((TextWebSocketFrame) frame).text();
//
//		System.out.println("服务端收到：" + request);
//		System.out.println(ctx.handler());
//		Class c= null;
//		try {
//			c = Class.forName("controller."+url);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		Object object= null;
//		try {
//			object = c.newInstance();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		Method method= null;
//		try {
//			method = c.getMethod("Return", String.class);
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		String json= null;
//		try {
//			json = (String) method.invoke(object,request);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
        String url=fuHr.uri().split("/")[1];
        System.out.println("url:" + url);
        ByteBuf byteBuf = fuHr.content();
        String data = byteBuf.toString(Charset.forName("utf-8"));
        String json = "";
        String resurl[] = url.split("/");
        logger.info("data:" + data + ",URL: " + url + ",URLsplit: [1]:" + resurl[1] + ",[2]:" + resurl[2] + ",[3]:" + resurl[3]);
        Class c= null;
        try {
           c=Class.forName("controller."+url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("===========全局异常错误!!!===========");
            return "{\"message\":\"Bad\"}";
        }
        Object object= null;
		try {
			object = c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Method method= null;
		try {
			method = c.getMethod("Return", String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
        return "{\"message\":\"Bad\"}";
        //        try {
//            Class<?> clazz=Class.forName("all."+resurl[1]+"."+resurl[2]);
//            System.out.println(clazz.getName());
//            Method method=clazz.getMethod(resurl[3],String.class);
//            json= (String) method.invoke(clazz.newInstance(),data);
//            return json;
//        } catch (Exception e) {
//            logger.error("===========全局异常错误!!!===========");
//            e.printStackTrace();
//            return "{\"message\":\"Bad\"}";
//        }
    }
}

