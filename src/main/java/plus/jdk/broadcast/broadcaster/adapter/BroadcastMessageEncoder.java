package plus.jdk.broadcast.broadcaster.adapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;

import java.net.InetSocketAddress;
import java.util.List;

import plus.jdk.broadcast.model.Monitor;
import plus.jdk.broadcast.properties.BroadCastProperties;

public class BroadcastMessageEncoder extends MessageToMessageEncoder<BroadcastMessage> {

    private final BroadCastProperties properties;

    public BroadcastMessageEncoder(BroadCastProperties broadCastProperties) {
        this.properties = broadCastProperties;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, BroadcastMessage msg, List<Object> out) throws Exception {
        if(properties.getMonitors() == null) {
            return;
        }
        for(Monitor monitor : properties.getMonitors()) {
            ByteBuf byteBuf = ctx.alloc().buffer(msg.getContent().length() + 1);
            byteBuf.writeBytes(msg.getContent().getBytes(CharsetUtil.UTF_8));
            InetSocketAddress address = new InetSocketAddress(monitor.getHost(), monitor.getPort());
            out.add(new DatagramPacket(byteBuf, address));
        }
    }
}
