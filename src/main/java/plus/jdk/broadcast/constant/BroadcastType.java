package plus.jdk.broadcast.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import plus.jdk.broadcast.broadcaster.IBroadcaster;
import plus.jdk.broadcast.broadcaster.IMessageMonitor;
import plus.jdk.broadcast.broadcaster.UdpMessageBroadcaster;
import plus.jdk.broadcast.broadcaster.UdpBroadcastMessageMonitor;

@Getter
@AllArgsConstructor
public enum BroadcastType {

    UDP_BROADCAST(UdpMessageBroadcaster.class, UdpBroadcastMessageMonitor.class, "广播模式"),
    UDP_MULTICAST(null, null, "组播模式(待实现)"),
    UDP_UNICAST(null, null, "单播模式(待实现)");

    private final Class<? extends IBroadcaster> broadcasterClazz;

    private final Class<? extends IMessageMonitor> monitorClazz;

    private final String desc;
}
