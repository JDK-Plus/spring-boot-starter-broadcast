package plus.jdk.broadcast.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.jdk.broadcast.broadcaster.UdpMessageBroadcaster;
import plus.jdk.broadcast.broadcaster.UdpBroadcastMessageMonitor;
import plus.jdk.broadcast.properties.BroadCastProperties;


@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "plus.jdk.broadcast", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(BroadCastProperties.class)
public class BroadCastAutoConfiguration implements DisposableBean, WebMvcConfigurer {

    private final BroadCastProperties broadCastProperties;

    public BroadCastAutoConfiguration(BroadCastProperties broadCastProperties) {
        this.broadCastProperties = broadCastProperties;
    }

    @Bean
    public BroadCastProperties broadCastProperties() {
        return broadCastProperties;
    }


    @Bean
    public UdpMessageBroadcaster udpMessageBroadcaster() {
        return new UdpMessageBroadcaster(broadCastProperties);
    }


    @Bean
    public UdpBroadcastMessageMonitor udpMessageMonitor() {
        return new UdpBroadcastMessageMonitor(broadCastProperties);
    }

    @Override
    public void destroy() {

    }
}
