package com.priceflow.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 33954
 * #Description Good
 * #Date: 2025/3/30 10:47
 */


/**
 * 爬虫获取的商品信息
 * 根据爬虫结果可手动修改
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description= "商品信息")
public class Good {
    private String description;
    // 图片链接
    private String img;
    private String shopName;
    // 平台：拼多多、京东、淘宝
    private String platform;
    private BigDecimal price;
    private String detailUrl;
    // 更多信息
}
