package plus.jdk.broadcast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monitor {

    /**
     * 当前broker的地址.ip或者域名均可.
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;
}
