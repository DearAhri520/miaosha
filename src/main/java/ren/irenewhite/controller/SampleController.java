package ren.irenewhite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.UserService;

/**
 * @author DearAhri520
 * @date 2022/5/31
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService service;

    @Autowired
    RedisManager redisService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello,imooc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Ahri");
        return "hello";
    }

    @RequestMapping("/db/insert")
    @ResponseBody
    public Result<Boolean> dbInsert() {
        return Result.success(true);
    }
}