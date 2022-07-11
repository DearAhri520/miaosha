package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.User;
import ren.irenewhite.service.GoodService;
import ren.irenewhite.service.SnipingGoodService;
import ren.irenewhite.service.UserService;

import java.util.List;

/**
 * @author DearAhri520
 * @date 2022/6/23
 */
@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    UserService userService;

    @Autowired
    GoodService goodService;

    @Autowired
    SnipingGoodService snipingGoodService;

    @RequestMapping("/to_list")
    public String toList(User user, Model model) {
        /*查询商品列表*/
        List<SnipingGood> goodInfos = snipingGoodService.getSnipingGoods();
        log.info("商品列表:{}", goodInfos);
        model.addAttribute("goodsList", goodInfos);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodId}")
    public String detail(User user, Model model, @PathVariable("goodId") long goodId) {
        model.addAttribute("user", user);
        SnipingGood snipingGood = snipingGoodService.getSnipingGoodById(goodId);
        log.info("秒杀商品:{}", snipingGood.toString());
        model.addAttribute("goodInfo", snipingGood);
        long start = snipingGood.getStartTime().getTime(),
                end = snipingGood.getEndTime().getTime(),
                now = System.currentTimeMillis();
        /*秒杀未开始*/
        int status, remainSeconds;
        if (now < start) {
            status = 0;
            remainSeconds = (int) ((start - now) / 1000);
        }
        /*秒杀已结束*/
        else if (now > end) {
            status = 2;
            remainSeconds = -1;
        }
        /*秒杀正在进行*/
        else {
            status = 1;
            remainSeconds = 0;
        }
        model.addAttribute("snipingStatus", status);
        model.addAttribute("remainSeconds", remainSeconds);
        log.info("remainSeconds:{}", remainSeconds);
        return "goods_detail";
    }
}