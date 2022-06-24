package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Select;
import ren.irenewhite.domain.Good;
import ren.irenewhite.pojo.GoodInfo;

public interface GoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    @Select("select * from goods where id = #{id}")
    GoodInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);
}