package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ren.irenewhite.pojo.GoodInfo;

import java.util.List;

/**
 * @author DearAhri520
 * @date 2022/6/24
 */
@Mapper
public interface GoodInfoDao {

    /**
     * 获取所有商品信息
     *
     * @return 所有商品信息
     */
    @Select("select goods.*,sg.sniping_price,sg.stock_count,sg.start_time,sg.end_time from sniping_goods sg left join goods on sg.id = goods.id")
    List<GoodInfo> getGoodsInfo();

    /**
     * 根据id获取商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    @Select("select goods.*,sg.sniping_price,sg.stock_count,sg.start_time,sg.end_time from sniping_goods sg left join goods on sg.id = goods.id where goods.id = #{id}")
    GoodInfo getGoodInfoById(long id);
}
