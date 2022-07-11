package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import ren.irenewhite.domain.SnipingGood;

import java.util.List;

@Mapper
public interface SnipingGoodDao {
    int deleteByPrimaryKey(Long id);

    int insert(SnipingGood record);

    int insertSelective(SnipingGood record);

    SnipingGood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SnipingGood record);

    int updateByPrimaryKey(SnipingGood record);

    /**
     * 获取所有秒杀商品信息
     * @return 所有秒杀商品信息
     */
    List<SnipingGood> getSnipingGoods();

    /**
     * 根据商品id获取秒杀商品信息
     *
     * @param id 商品id
     * @return 秒杀商品信息
     */
    SnipingGood getSnipingGoodById(Long id);

    /**
     * 根据商品id减少秒杀商品库存
     * @param id 商品id
     */
    int updateSnipingStockCountById(Long id);
}