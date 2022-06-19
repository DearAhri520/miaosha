package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 * @date 2022/5/31
 */
@Mapper
public interface UserDAO {
    /**
     * 根据id获取一个用户
     *
     * @param id id
     * @return 用户
     */
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") int id);

    /**
     * 插入一个用户
     *
     * @param user 用户
     * @return 插入个数
     */
    @Insert("insert into user(id,name) values(#{id},#{name})")
    int insert(User user);
}
