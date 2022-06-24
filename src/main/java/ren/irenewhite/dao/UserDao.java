package ren.irenewhite.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 */
@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Long id);

    @Insert("insert into user (id,nickname, `password`, salt, head, register_date, last_login_date, login_count)" +
            "values (#{id},#{nickname}, #{password}, #{salt}, #{head}, #{registerDate}, #{lastLoginDate}, #{loginCount})")
    int insert(User record);

    int insertSelective(User record);

    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}