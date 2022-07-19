package ren.irenewhite.result;

import lombok.Getter;

/**
 * @author DearAhri520
 * @date 2022/5/31
 */
@Getter
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * 通用错误码
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常 : %s");

    /**
     * 登录模块
     */
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或已失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "密码不能为空");
    public static CodeMsg PASSWORD_LENGTH_ERROR = new CodeMsg(500212, "密码长度必须大于等于6小于等于16");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500213, "密码错误");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500214, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500215, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500216, "手机号不存在");
    //商品模块 5003XX

    /**
     * 订单模块 5004XX
     */
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");


    /**
     * 秒杀模块 5005XX
     */
    public static CodeMsg GOOD_SNIPING_OVER = new CodeMsg(500500, "商品库存已秒杀完毕");
    public static CodeMsg CANT_REPEAT_SNIPING = new CodeMsg(500501, "您已秒杀成功，不能重复秒杀该商品");


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        this.msg = String.format(this.msg, args);
        return this;
    }
}