package ren.irenewhite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ren.irenewhite.service.SnipingService;

/**
 * @author DearAhri520
 * @date 2022/7/21
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    SnipingService snipingService;

    @RequestMapping("")
    public void test() {
        snipingService.reduceStock(1L);
    }
}
