package plus.jdk.broadcast.broadcaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastMessage {

    /**
     * 消息内容
     */
    private String content;
}
