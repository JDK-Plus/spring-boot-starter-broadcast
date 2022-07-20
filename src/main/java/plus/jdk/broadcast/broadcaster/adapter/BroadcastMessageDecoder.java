package plus.jdk.broadcast.broadcaster.adapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;
import plus.jdk.broadcast.properties.BroadCastProperties;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class BroadcastMessageDecoder extends MessageToMessageDecoder<DatagramPacket> {


    private final BroadCastProperties broadCastProperties;

    public BroadcastMessageDecoder(BroadCastProperties broadCastProperties) {
        this.broadCastProperties = broadCastProperties;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = msg.content();
        BroadcastMessage broadcastMessage = new BroadcastMessage();
        broadcastMessage.setContent(byteBuf.toString(StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));
        out.add(broadcastMessage);
    }
}
