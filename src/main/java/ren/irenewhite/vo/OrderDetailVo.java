package ren.irenewhite.vo;

import lombok.Data;
import ren.irenewhite.domain.Good;
import ren.irenewhite.domain.Order;

/**
 * @author DearAhri520
 * @date 2022/7/17
 */
@Data
public class OrderDetailVo {
    private Order order;
    private Good good;
}
