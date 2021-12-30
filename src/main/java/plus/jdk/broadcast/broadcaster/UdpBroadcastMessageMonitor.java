package plus.jdk.broadcast.broadcaster;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import plus.jdk.broadcast.broadcaster.adapter.BroadcastMessageDecoder;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;
import plus.jdk.broadcast.common.IMessageProcessor;
import plus.jdk.broadcast.constant.BroadcastType;
import plus.jdk.broadcast.properties.BroadCastProperties;

import java.net.InetSocketAddress;

@Slf4j
public class UdpBroadcastMessageMonitor implements IMessageMonitor {

    @Getter
    private final BroadCastProperties properties;

    private final EventLoopGroup group;

    private final Bootstrap bootstrap;

    private SimpleChannelInboundHandler<BroadcastMessage> inboundHandler;

    public UdpBroadcastMessageMonitor(BroadCastProperties properties) {
        this.properties = properties;
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.option(ChannelOption.SO_BROADCAST, true);
        bootstrap.localAddress(new InetSocketAddress(properties.getMonitorPort()));
        log.info("========>>> broadcast server started, listen port: {} <<<========", properties.getMonitorPort());
    }

    @Override
    public void subscribe(IMessageProcessor processor) {
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new BroadcastMessageDecoder(properties));
                pipeline.addLast(new SimpleChannelInboundHandler<BroadcastMessage>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, BroadcastMessage msg) {
                        processor.processMessage(ctx, msg);
                    }
                });
            }
        });
        Channel channel = bootstrap.bind().syncUninterruptibly().channel();
        try {
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UdpBroadcastMessageMonitor something went wrong, msg:{}", e.getMessage());
        } finally {
            group.shutdownGracefully();
        }
    }
}
