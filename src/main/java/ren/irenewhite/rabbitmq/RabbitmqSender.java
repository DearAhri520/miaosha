package ren.irenewhite.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.utils.JSONUtil;

/**
 * @author DearAhri520
 * @date 2022/7/20
 */
@Service
@Slf4j
public class RabbitmqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSnipingMessage(SnipingMessage message) {
        String s = JSONUtil.toJSON(message);
        amqpTemplate.convertAndSend(RabbitmqConfig.SNIPING_QUEUE_NAME, s);
        log.info("send sniping message:{}", s);
    }
}
