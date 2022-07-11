package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import ren.irenewhite.domain.SnipingOrder;

@Mapper
public interface SnipingOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(SnipingOrder record);

    int insertSelective(SnipingOrder record);

    SnipingOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SnipingOrder record);

    int updateByPrimaryKey(SnipingOrder record);

    /**
     * 根据用户id和商品id返回一个秒杀订单
     * @param userId 用户id
     * @param goodId 商品id
     * @return 秒杀订单
     */
    SnipingOrder selectByUserIdAndGoodId(long userId,long goodId);
}