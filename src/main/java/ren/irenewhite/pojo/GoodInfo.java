package ren.irenewhite.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ren.irenewhite.domain.Good;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品信息
 *
 * @author DearAhri520
 * @date 2022/6/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class GoodInfo extends Good {
    /**
     * 秒杀价
     */
    private BigDecimal snipingPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;
}
