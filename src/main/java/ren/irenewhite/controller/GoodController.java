package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.User;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.redis.key.GoodListKey;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.GoodService;
import ren.irenewhite.service.SnipingGoodService;
import ren.irenewhite.service.UserService;
import ren.irenewhite.vo.GoodDetailVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    RedisManager redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request,
                         HttpServletResponse response,
                         User user,
                         Model model) {
        /*查询商品列表*/
        List<SnipingGood> goodInfos = snipingGoodService.getSnipingGoods();
        log.info("商品列表:{}", goodInfos);
        model.addAttribute("goodsList", goodInfos);
        String html = redisService.get(GoodListKey.GoodList, "goodList", String.class);
        if (ObjectUtils.isNotEmpty(html)) {
            log.info("html缓存已查询到");
            return html;
        }
        IWebContext iWebContext = new WebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap());
        /*缓存不存在,则手动渲染*/
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", iWebContext);
        log.info("渲染html页面");
        /*保存到缓存中*/
        if (ObjectUtils.isNotEmpty(html)) {
            log.info("goods_list.html页面:{} 保存到Redis缓存",html);
            redisService.set(GoodListKey.GoodList, "goodList", html);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodId}", produces = "application/json")
    @ResponseBody
    public Result<GoodDetailVo> detail(User user,
                                       @PathVariable("goodId") long goodId) {
        SnipingGood snipingGood = snipingGoodService.getSnipingGoodById(goodId);
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
        GoodDetailVo goodDetailVo = new GoodDetailVo(
                user, snipingGood, status, remainSeconds
        );
        return Result.success(goodDetailVo);
    }
}