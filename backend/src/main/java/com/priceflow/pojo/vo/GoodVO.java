package com.priceflow.pojo.vo;

import com.priceflow.pojo.entity.Good;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 33954
 * #Description GoodVO
 * #Date: 2025/3/31 10:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description= "商品搜索信息")
public class GoodVO extends Good {
    private boolean isSubscribed;

    public GoodVO(Good good) {
        this.setDescription(good.getDescription());
        this.setImg(good.getImg());
        this.setShopName(good.getShopName());
        this.setPlatform(good.getPlatform());
        this.setPrice(good.getPrice());
        this.setDetailUrl(good.getDetailUrl());
    }
}
