package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import ren.irenewhite.domain.Order;

@Mapper
public interface OrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}