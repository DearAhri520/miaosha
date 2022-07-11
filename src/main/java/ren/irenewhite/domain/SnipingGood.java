package ren.irenewhite.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * sniping_goods
 * @author DearAhri520
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SnipingGood extends Good{
    /**
     * 秒杀价
     */
    private BigDecimal snipingPrice;

    /**
     * 库存数量
     */
    private Integer snipingGoodStock;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;
}