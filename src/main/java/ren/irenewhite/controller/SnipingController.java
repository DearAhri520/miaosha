package ren.irenewhite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.SnipingOrder;
import ren.irenewhite.domain.User;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.service.*;

/**
 * @author DearAhri520
 * @date 2022/6/26
 */
@Controller
@RequestMapping("/sniping")
public class SnipingController {
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

    @RequestMapping("do_sniping")
    public String list(Model model, User user, @RequestParam("goodId") long goodId) {
        model.addAttribute("user", user);
        /*todo:改用登录拦截器实现登录拦截*/
        if (user == null) {
            return "login";
        }
        SnipingGood snipingGood = snipingGoodService.getSnipingGoodById(goodId);
        /*当秒杀商品库存为0时，返回*/
        if (snipingGood.getSnipingGoodStock() <= 0) {
            model.addAttribute("errormsg", CodeMsg.GOOD_SNIPING_OVER);
            return "sniping_fail";
        }
        /*当用户已经秒杀成功时，防止秒杀到多件商品*/
        SnipingOrder order = snipingOrderService.getSnipingByUserIdAndGoodId(user.getId(), goodId);
        /*该用户已经秒杀成功，返回*/
        if (order != null) {
            model.addAttribute("errormsg", CodeMsg.CANT_REPEAT_SNIPING);
            return "sniping_fail";
        }
        /*秒杀逻辑*/
        Order orderInfo = snipingService.sniping(user, snipingGood);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goodInfo", snipingGood);
        return "order_detail";
    }
}