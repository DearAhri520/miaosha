package ren.irenewhite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.irenewhite.dao.SnipingOrderDao;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 * @date 2022/6/26
 * <p>
 * 秒杀订单service
 */
@Service
@Slf4j
public class SnipingOrderService {
    @Autowired
    SnipingOrderDao snipingOrderDao;

    public SnipingOrder getSnipingByUserIdAndGoodId(long userId, long goodId) {
        return snipingOrderDao.selectByUserIdAndGoodId(userId, goodId);
    }

    public int insertSnipingOrder(SnipingOrder snipingOrder) {
        return snipingOrderDao.insert(snipingOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    public SnipingOrder createSnipingOrder(Order order){
        log.info(order.toString());
        SnipingOrder snipingOrder = new SnipingOrder();
        snipingOrder.setOrderId(order.getId());
        snipingOrder.setUserId(order.getUserId());
        snipingOrder.setGoodId(order.getGoodId());
        snipingOrderDao.insert(snipingOrder);
        return snipingOrder;
    }
}
