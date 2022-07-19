package ren.irenewhite.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * order_info
 * @author DearAhri520
 */
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 收货地址id
     */
    private Long deliveryAddressId;

    /**
     * 冗余的商品名称
     */
    private String goodName;

    /**
     * 商品数量
     */
    private Integer goodCount;

    /**
     * 商品单价
     */
    private BigDecimal goodPrice;

    /**
     * 1:pc 2:android 3:iOS
     */
    private Byte orderChannel;

    /**
     * 订单状态 0:新建未支付 1:已支付 2:已发货 3:已收货 4:已退款 5:已完成
     */
    private Byte status;

    /**
     * 订单创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

    private static final long serialVersionUID = 1L;
}