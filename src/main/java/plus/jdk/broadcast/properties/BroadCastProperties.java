package plus.jdk.broadcast.properties;

import io.netty.handler.logging.LogLevel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import plus.jdk.broadcast.constant.BroadcastType;
import plus.jdk.broadcast.model.Monitor;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "plus.jdk.broadcast")
public class BroadCastProperties {

    /**
     * 是否开启
     */
    private Boolean enabled = false;

    /**
     * 要监听广播的端口
     */
    private Integer monitorPort = 10300;

    /**
     * 广播端口
     */
    private Integer broadcastPort = 0;

    /**
     * 接收广播的brokers列表
     */
    private List<Monitor> monitors = new ArrayList<>();

    /**
     * 广播类型
     */
    private BroadcastType broadcastType = BroadcastType.UDP_BROADCAST;

    /**
     * 日志类型
     */
    private LogLevel logging = LogLevel.DEBUG;
}
