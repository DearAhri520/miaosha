package ren.irenewhite.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.SnipingGoodService;
import ren.irenewhite.service.SnipingOrderService;
import ren.irenewhite.service.SnipingService;
import ren.irenewhite.utils.JSONUtil;

/**
 * @author DearAhri520
 * @date 2022/7/20
 */
@Service
@Slf4j
public class RabbitmqReceiver {
    @Autowired
    SnipingGoodService snipingGoodService;

    @Autowired
    SnipingOrderService snipingOrderService;

    @Autowired
    SnipingService snipingService;

    @RabbitListener(queues = RabbitmqConfig.SNIPING_QUEUE_NAME)
    public void receive(String message) {
        log.info("receive:{}", message);
        SnipingMessage m = JSONUtil.toBean(message, SnipingMessage.class);
        User user = m.getUser();
        long goodId = m.getSnipingGoodId();

        SnipingGood snipingGood = snipingGoodService.getSnipingGoodById(goodId);
         /*当秒杀商品库存为0时，返回*/
        if (snipingGood.getSnipingGoodStock() <= 0) {
            return;
        }
        /*当用户已经秒杀成功时，防止秒杀到多件商品*/
        SnipingOrder order = snipingOrderService.getSnipingByUserIdAndGoodId(user.getId(), goodId);
        /*该用户已经秒杀成功，返回*/
        if (order != null) {
            return;
        }
        /*秒杀逻辑*/
        Order orderInfo = snipingService.sniping(user, snipingGood);
    }
}