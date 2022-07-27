package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.irenewhite.domain.*;
import ren.irenewhite.rabbitmq.RabbitmqSender;
import ren.irenewhite.rabbitmq.SnipingMessage;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.redis.key.GoodListKey;
import ren.irenewhite.redis.key.SnipingGoodKey;
import ren.irenewhite.redis.key.SnipingKey;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.*;
import ren.irenewhite.utils.MD5Util;
import ren.irenewhite.utils.UUIDUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.nio.Buffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author DearAhri520
 * @date 2022/6/26
 */
@Controller
@Slf4j
@RequestMapping("/sniping")
public class SnipingController implements InitializingBean {
    @Autowired
    GoodService goodService;

    @Autowired
    SnipingGoodService snipingGoodService;

    @Autowired
    OrderService orderService;

    @Autowired
    SnipingOrderService snipingOrderService;

    @Autowired
    SnipingService snipingService;

    @Autowired
    RedisManager redisService;

    @Autowired
    RabbitmqSender sender;

    /**
     * 商品id->商品库存是否秒杀完毕
     */
    private Map<Long, AtomicBoolean> localOverMap = new ConcurrentHashMap<>();

    @ResponseBody
    @RequestMapping(value = "/do_sniping")
    public Result<Integer> sniping(User user, @RequestParam("goodId") long goodId, @RequestParam("path") String path) {
        /*todo:改用登录拦截器实现登录拦截*/
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        /*验证path*/
        boolean check = snipingService.checkPath(user, goodId, path);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        /*本地检查库存*/
        AtomicBoolean over = localOverMap.get(goodId);
        if (over.get()) {
            return Result.error(CodeMsg.GOOD_SNIPING_OVER);
        }
        /*redis预减库存*/
        int stock = redisService.get(SnipingGoodKey.SnipingGoodStock, "" + goodId, Integer.class);
        if (stock <= 0) {
            localOverMap.get(goodId).set(true);
            return Result.error(CodeMsg.GOOD_SNIPING_OVER);
        }
        redisService.decr(SnipingGoodKey.SnipingGoodStock, "" + goodId, 1);
        /*当用户已经秒杀成功时，防止秒杀到多件商品*/
        SnipingOrder order = snipingOrderService.getSnipingByUserIdAndGoodId(user.getId(), goodId);
        /*该用户已经秒杀成功，返回*/
        if (order != null) {
            return Result.error(CodeMsg.CANT_REPEAT_SNIPING);
        }
        /*消息入队*/
        SnipingMessage message = new SnipingMessage(user, goodId);
        sender.sendSnipingMessage(message);
        return Result.success(0);
    }

    /**
     * 返回秒杀结果
     * 秒杀成功:返回orderId
     * 秒杀排队中:返回0
     * 秒杀失败:返回-1
     */
    @RequestMapping(value = "/result")
    @ResponseBody
    public Result<Long> snipingResult(User user, @RequestParam("goodId") long goodId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = snipingService.getSnipingResult(user.getId(), goodId);
        return Result.success(result);
    }

    @RequestMapping(value = "/path")
    @ResponseBody
    public Result<String> getSnipingPath(User user, @RequestParam("goodId") long goodId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        String path = snipingService.createSnipingPath(user, goodId);
        return Result.success(path);
    }

    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public Result<String> getSnipingVerifyCode(HttpServletResponse response, User user, @RequestParam("goodId") long goodId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        BufferedImage image = snipingService.createVerifyCode();
        try (OutputStream ops = response.getOutputStream()) {
            ImageIO.write(image, "jpg", ops);
        } catch (Exception e) {
            log.warn("验证码图片传输错误", e);
            return null;
        }
        return null;
    }


    /**
     * Bean初始化时调用
     * <p>
     * 获取所有秒杀商品并将数量加载到redis当中
     *
     * @throws Exception 异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SnipingGood> snipingGoodList = snipingGoodService.getSnipingGoods();
        if (snipingGoodList == null || snipingGoodList.size() == 0) {
            return;
        }
        for (SnipingGood g : snipingGoodList) {
            redisService.set(SnipingGoodKey.SnipingGoodStock, g.getId() + "", g.getGoodStock());
            log.info("秒杀商品:{} 秒杀库存{}存至redis缓存", g.getId(), g.getSnipingGoodStock());
            localOverMap.put(g.getId(), new AtomicBoolean(false));
        }
    }
}