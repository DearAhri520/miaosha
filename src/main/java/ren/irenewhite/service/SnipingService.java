package ren.irenewhite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;

import java.util.Date;

/**
 * @author DearAhri520
 * @date 2022/6/26
 */
@Service
@Slf4j
public class SnipingService {
    @Autowired
    SnipingGoodService snipingGoodService;

    @Autowired
    GoodService goodService;

    @Autowired
    SnipingOrderService snipingOrderService;

    @Autowired
    OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    public Order sniping(User user, SnipingGood snipingGood) {
        /*1. 减库存*/
        reduceStock(snipingGood.getId());
        /*2. 创建秒杀订单*/
        return createSnipingOrder(user, snipingGood);
    }

    @Transactional(rollbackFor = Exception.class)
    void reduceStock(long id){
        /*秒杀商品库存减一*/
        snipingGoodService.reduceStockById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Order createSnipingOrder(User user, SnipingGood snipingGood){
        /*创建普通订单*/
        Order order = orderService.createOrder(user,snipingGood);
        /*创建秒杀订单并关联普通订单*/
        snipingOrderService.createSnipingOrder(order);
        return order;
    }
}
