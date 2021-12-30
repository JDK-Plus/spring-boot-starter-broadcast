package plus.jdk.broadcast.broadcaster;

import io.netty.channel.ChannelFuture;
import plus.jdk.broadcast.broadcaster.model.BroadcastMessage;

public interface IBroadcaster {

    ChannelFuture publish(BroadcastMessage data) throws InterruptedException;
}
