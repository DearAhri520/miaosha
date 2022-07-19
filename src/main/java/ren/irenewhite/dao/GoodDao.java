package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ren.irenewhite.domain.Good;

import java.util.List;

/**
 * @author DearAhri520
 */
@Mapper
public interface GoodDao{
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    @Select("select * from good where id = #{id}")
    Good selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);

    @Update("update good set good_stock = good_stock - 1 where id = #{id}")
    int updateStockCountById(Long id);

    @Select("select * from good")
    List<Good> getGoods();
}