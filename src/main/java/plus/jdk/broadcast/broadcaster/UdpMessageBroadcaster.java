package plus.jdk.broadcast.broadcaster;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import plus.jdk.broadcast.broadcaster.adapter.BroadcastMessageEncoder;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;
import plus.jdk.broadcast.properties.BroadCastProperties;

@Slf4j
public class UdpMessageBroadcaster implements IBroadcaster {

    private final BroadCastProperties properties;

    private final EventLoopGroup group;

    private final Bootstrap bootstrap;

    private Channel channel;

    private Boolean started = false;

    @SneakyThrows
    public UdpMessageBroadcaster(BroadCastProperties properties) {
        this.properties = properties;
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class);
        bootstrap.option(ChannelOption.SO_BROADCAST, true);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new BroadcastMessageEncoder(properties));
            }
        });
    }

    private void start() {
        started = true;
        this.channel = bootstrap.bind(properties.getBroadcastPort()).syncUninterruptibly().channel();
    }

    @Override
    public ChannelFuture publish(BroadcastMessage data) {
        if (!started) {
            this.start();
        }
        return channel.writeAndFlush(data);
    }

    public void shutdown() {
        group.shutdownGracefully();
    }
}
