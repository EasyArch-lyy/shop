package netty.SyncHttpResponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;

public class HttpClient {
    private ClientHandler clientHandler = new ClientHandler();
    private String url;
    private URI uri;
    public HttpClient(String url) {
        this.url = url;
    }
    public void connect() throws Exception {
        uri = new URI(url);
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpRequestEncoder()).addLast(new HttpResponseDecoder()).addLast(clientHandler);
            }
        });
        Channel channel = b.connect(uri.getHost(), uri.getPort() < 0 ? 80 : uri.getPort()).sync().channel();
        while (!channel.isActive()) {
            Thread.sleep(1000);
        }
    }
    public String getBody() throws Exception {
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString());
        request.headers().set(HttpHeaders.Names.HOST, uri.getHost());
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
        ChannelPromise promise = clientHandler.sendMessage(request);
        promise.await();
        return clientHandler.getData();
    }
}
