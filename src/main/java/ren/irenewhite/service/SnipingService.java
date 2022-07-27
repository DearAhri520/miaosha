package ren.irenewhite.service;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.redis.RedisService;
import ren.irenewhite.redis.key.SnipingGoodKey;
import ren.irenewhite.redis.key.SnipingKey;
import ren.irenewhite.utils.MD5Util;
import ren.irenewhite.utils.UUIDUtil;

import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
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

    @Autowired
    RedisManager redisService;

    @Autowired
    Producer producer;

    @Transactional(rollbackFor = Exception.class)
    public Order sniping(User user, SnipingGood snipingGood) {
        /*1. 减库存*/
        boolean success = reduceStock(snipingGood.getId());
        /*2. 创建秒杀订单*/
        if (success) {
            return createSnipingOrder(user, snipingGood);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean reduceStock(long id) {
        /*mysql秒杀商品库存减一*/
        int ret = snipingGoodService.reduceStockById(id);
        /*redis秒杀商品库存减一*/
        SnipingGood snipingGood = redisService.get(SnipingGoodKey.SnipingGood, id + "", SnipingGood.class);
        snipingGood.setSnipingGoodStock(snipingGood.getGoodStock() - 1);
        redisService.set(SnipingGoodKey.SnipingGood, id + "", snipingGood);
        return ret > 0;
    }

    /**
     * 创建秒杀订单
     *
     * @param user        用户
     * @param snipingGood 秒杀商品
     * @return 生成的订单
     */
    @Transactional(rollbackFor = Exception.class)
    public Order createSnipingOrder(User user, SnipingGood snipingGood) {
        /*创建普通订单*/
        Order order = orderService.createOrder(user, snipingGood);
        /*创建秒杀订单并关联普通订单*/
        snipingOrderService.createSnipingOrder(order);
        return order;
    }

    /**
     * 返回秒杀结果
     * 秒杀成功:返回orderId
     * 秒杀排队中:返回0
     * 秒杀失败:返回-1
     *
     * @param userId 用户id
     * @param goodId 商品id
     * @return 秒杀结果
     */
    public long getSnipingResult(long userId, long goodId) {
        SnipingOrder order = snipingOrderService.getSnipingByUserIdAndGoodId(userId, goodId);
        /*秒杀成功*/
        if (order != null) {
            return order.getOrderId();
        } else {
            SnipingGood snipingGood = snipingGoodService.getSnipingGoodById(goodId);
            /*
             * 商品库存小于等于0,返回-1,秒杀失败
             * 商品库存大于0,返回0,排队中
             */
            if (snipingGood.getSnipingGoodStock() <= 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public String createSnipingPath(User user, long goodId) {
        String uuid = MD5Util.md5(UUIDUtil.uuid());
        redisService.set(SnipingKey.SnipingPath, user.getId() + "_" + goodId, uuid);
        return uuid;
    }

    public boolean checkPath(User user, long goodId, String path) {
        String p = redisService.get(SnipingKey.SnipingPath, user.getId() + "_" + goodId, String.class);
        if (path == null || p == null) {
            return false;
        }
        return p.equals(path);
    }

    /**
     * 生成验证码
     */
    public BufferedImage createVerifyCode() {
        String text = producer.createText();
        return producer.createImage(text);
    }
}
