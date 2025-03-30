package com.priceflow.mapper;

import com.priceflow.pojo.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 33954
 * #Description NoticeMapper
 * #Date: 2025/3/30 20:22
 */
@Mapper
public interface NoticeMapper {
    @Insert("insert into notice (user_id, description, present_price, target_price, shop_name, img) " +
            "values (#{userId}, #{description}, #{presentPrice}, #{targetPrice}, #{shopName}, #{img})")
    void insert(Notice notice);

    @Delete("delete from notice where id = #{id}")
    void deleteById(Integer id);

    @Update("update notice set target_price = #{targetPrice} where id = #{id}")
    void updateTargetPrice(Integer id, BigDecimal targetPrice);

    @Select("select * from notice where user_id = #{userId}")
    List<Notice> selectByUserId(Integer user);
}
