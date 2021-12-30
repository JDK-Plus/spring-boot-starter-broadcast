package plus.jdk.broadcast.common;

import io.netty.channel.ChannelHandlerContext;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;

public interface IMessageProcessor {
    Boolean processMessage(ChannelHandlerContext ctx, BroadcastMessage msg);
}
