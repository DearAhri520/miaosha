package ren.irenewhite.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * sniping_order
 * @author 
 */
@Data
public class SnipingOrder implements Serializable {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    private static final long serialVersionUID = 1L;
}