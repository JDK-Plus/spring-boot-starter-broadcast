package plus.jdk.broadcast.broadcaster;

import plus.jdk.broadcast.common.IMessageProcessor;

public interface IMessageMonitor {
    void subscribe(IMessageProcessor processor);
}
