package ren.irenewhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.irenewhite.dao.OrderDao;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;

import java.util.Date;

/**
 * @author DearAhri520
 * @date 2022/6/26
 * <p>
 * 订单service
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    SnipingOrderService snipingOrderService;

    @Transactional
    public Order createOrder(User user, SnipingGood snipingGood) {
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setDeliveryAddressId(0L);
        order.setGoodCount(1);
        order.setGoodId(snipingGood.getId());
        order.setGoodName(snipingGood.getGoodName());
        /*商品秒杀价格*/
        order.setGoodPrice(snipingGood.getSnipingPrice());
        order.setOrderChannel((byte) 1);
        /*todo:修改为枚举类型表示*/
        order.setStatus((byte) 0);
        order.setUserId(user.getId());
        long id = orderDao.insert(order);
        order.setId(id);
        return order;
    }
}
