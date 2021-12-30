package plus.jdk.broadcast.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;
import plus.jdk.broadcast.properties.BroadCastProperties;


@Slf4j
public class UdpServerHandler extends SimpleChannelInboundHandler<BroadcastMessage> {

    private BroadCastProperties broadCastProperties;

    public UdpServerHandler(BroadCastProperties broadCastProperties){
        this.broadCastProperties = broadCastProperties;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BroadcastMessage msg) throws Exception {
        log.info("============>>>>>>>>>>>>> read message: {} {}", ctx, msg);
    }
}
