package ren.irenewhite.dao;

import ren.irenewhite.domain.SnipingOrder;

public interface SnipingOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(SnipingOrder record);

    int insertSelective(SnipingOrder record);

    SnipingOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SnipingOrder record);

    int updateByPrimaryKey(SnipingOrder record);
}