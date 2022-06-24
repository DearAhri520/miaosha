package ren.irenewhite.dao;

import ren.irenewhite.domain.SnipingGood;

public interface SnipingGoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(SnipingGood record);

    int insertSelective(SnipingGood record);

    SnipingGood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SnipingGood record);

    int updateByPrimaryKey(SnipingGood record);
}