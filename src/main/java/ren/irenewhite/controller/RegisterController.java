package ren.irenewhite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.irenewhite.pojo.LoginUser;
import ren.irenewhite.result.Result;
import ren.irenewhite.service.UserService;

import javax.validation.Valid;

/**
 * @author DearAhri520
 * @date 2022/6/23
 */
@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String toRegister() {
        return "register";
    }

    @ResponseBody
    @RequestMapping("/do_register")
    public Result<Boolean> doRegister(@Valid LoginUser user) {
        log.info(user.toString());
        boolean success = userService.register(user);
        return Result.success(success);
    }
}