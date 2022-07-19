package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ren.irenewhite.domain.Order;

@Mapper
public interface OrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 根据userId与goodId获取订单
     * @param userId userId
     * @param goodId goodId
     * @return 订单信息
     */
    @Select("select * from order where user_id = #{userId} and good_id = #{goodId}")
    Order selectByUserIdAndGoodId(long userId,long goodId);
}