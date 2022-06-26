package ren.irenewhite.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * goods
 * @author DearAhri520
 */
@Data
public class Good implements Serializable {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品标题
     */
    private String goodTitle;

    /**
     * 商品图片
     */
    private String goodImg;

    /**
     * 商品的详情介绍
     */
    private String goodDetail;

    /**
     * 商品单价
     */
    private BigDecimal goodPrice;

    /**
     * 商品库存,-1表示无限制
     */
    private Integer goodStock;

    private static final long serialVersionUID = 470137091573184783L;
}