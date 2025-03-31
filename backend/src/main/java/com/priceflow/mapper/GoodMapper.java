package com.priceflow.mapper;

import com.priceflow.pojo.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 33954
 * #Description GoodMapper
 * #Date: 2025/3/31 10:46
 */
@Mapper
public interface GoodMapper {

    @Insert("insert into history(user_id,content) values(#{userId},#{content})")
    void insert(History history);

    @Select("select * from history where user_id=#{userId}")
    List<History> selectByUserId(Integer userId);
}
