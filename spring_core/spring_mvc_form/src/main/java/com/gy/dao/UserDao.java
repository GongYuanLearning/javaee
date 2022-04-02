package com.gy.dao;

import com.gy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface UserDao {
    @Insert("insert into user(id, username, pwdHash, email, phone) values(#{id}, #{username}, #{pwdHash}, #{email}, #{phone})")
    @SelectKey(
            statement = "select if(max(id) is null, 1, max(id)+1) as newId from user",
            keyProperty = "id",
            before = true,
            resultType = int.class)
    void add(User user);
    @Select("select * from user where id=#{id}")
    User getById(long id);

    @Select("SELECT * from user WHERE username=#{username}")
    User findByUsername(String username);
}
