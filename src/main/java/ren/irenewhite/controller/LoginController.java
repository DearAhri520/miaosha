package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.irenewhite.pojo.LoginUser;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.UserService;
import ren.irenewhite.utils.ValidatorUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author DearAhri520
 * @date 2022/6/19
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    RedisManager redisService;

    @RequestMapping("to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginUser user) {
        log.info(user.toString());
        userService.login(response,user);
        return Result.success(true);
    }
}
