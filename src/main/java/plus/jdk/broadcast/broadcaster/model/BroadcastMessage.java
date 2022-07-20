package plus.jdk.broadcast.broadcaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.jdk.broadcast.model.Monitor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastMessage {

    /**
     * 消息内容
     */
    private byte[] content;

    /**
     * 需要追加的广播目标机器的列表
     */
    private List<Monitor> monitorList = new ArrayList<>();

    public BroadcastMessage(byte[] content) {
        this.content = content;
    }
}
