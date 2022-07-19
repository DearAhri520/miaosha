package ren.irenewhite.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 * @date 2022/7/15
 */
@Data
@AllArgsConstructor
public class GoodDetailVo {
    private User user;
    private SnipingGood snipingGood;
    private int snipingStatus;
    private int remainSeconds;
}
