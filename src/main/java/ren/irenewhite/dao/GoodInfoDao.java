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
    List<GoodInfo> getGoodsInfo();

    /**
     * 根据id获取商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    GoodInfo getGoodInfoById(long id);
}
