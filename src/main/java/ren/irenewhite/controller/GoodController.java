package ren.irenewhite.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ren.irenewhite.domain.User;
import ren.irenewhite.service.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author DearAhri520
 * @date 2022/6/23
 */
@Controller
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    UserService userService;

    @RequestMapping("/to_list")
    public String toList(@CookieValue(value = UserService.COOKIE_NAME_TOKEN,required = false) String cookieToken,
                         @RequestParam(value = UserService.COOKIE_NAME_TOKEN,required = false) String paramToken,
                         Model model,
                         HttpServletResponse response){
        /*如果token为空,则返回登录页面*/
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        User user = userService.getByToken(response,token);
        model.addAttribute("user",user);
        return "goods_list";
    }
}
