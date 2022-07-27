package ren.irenewhite.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DearAhri520
 * @date 2022/7/20
 */
@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_NAME = "queue";
    public static final String SNIPING_QUEUE_NAME = "snipingQueue";

    @Bean
    public Queue queue() {
        return new Queue(SNIPING_QUEUE_NAME, true);
    }
}
