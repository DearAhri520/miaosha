package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.irenewhite.domain.Good;
import ren.irenewhite.domain.Order;
import ren.irenewhite.domain.User;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.GoodService;
import ren.irenewhite.service.OrderService;
import ren.irenewhite.vo.OrderDetailVo;

/**
 * @author DearAhri520
 * @date 2022/7/17
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodService goodService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> detail(User user, @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodId = order.getGoodId();
        Good good = goodService.getGoodById(goodId);
        OrderDetailVo orderDetailVo = new OrderDetailVo() {{
            setOrder(order);
            setGood(good);
        }};
        log.info(orderDetailVo.toString());
        return Result.success(orderDetailVo);
    }
}
