package com.priceflow.mapper;

import com.priceflow.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author 33954
 * #Description UserMapper
 * #Date: 2025/3/30 12:07
 */
@Mapper
public interface UserMapper {

    User select(User user);

    @Insert("INSERT INTO user (username, password, email) VALUES (#{username}, #{password}, #{email})")
    int insert(User user);
}
