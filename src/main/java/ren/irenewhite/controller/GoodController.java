package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ren.irenewhite.domain.User;
import ren.irenewhite.pojo.GoodInfo;
import ren.irenewhite.service.GoodService;
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

    @RequestMapping("/to_list")
    public String toList(User user, Model model) {
        /*查询商品列表*/
        List<GoodInfo> goodInfos = goodService.getGoodsInfo();
        model.addAttribute("goodsList", goodInfos);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(User user, Model model, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        GoodInfo goodInfo = goodService.getGoodInfoById(goodsId);
        model.addAttribute("goodInfo", goodInfo);
        log.info(goodInfo.toString());
        long start = goodInfo.getStartTime().getTime(),
                end = goodInfo.getEndTime().getTime(),
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
        log.info("remainSeconds:{}",remainSeconds);
        return "goods_detail";
    }
}