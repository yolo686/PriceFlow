package com.priceflow.mapper;

import com.priceflow.pojo.entity.Subscription;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 33954
 * #Description SubcriptionMapper
 * #Date: 2025/3/30 19:17
 */
@Mapper
public interface SubscriptionMapper {

    void insert(Subscription subscription);

    @Delete("delete from subscription where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from subscription where user_id = #{userId} and description = #{description} and shop_name = #{shopName} and platform = #{platform}")
    Subscription selectOne(Subscription subscription);

    @Select("select * from subscription where user_id = #{userId}")
    List<Subscription> selectList(Integer user);
}
