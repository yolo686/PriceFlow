package com.priceflow.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 33954
 * #Description NoticeDTO
 * #Date: 2025/3/30 20:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description= "商品降价通知")
public class NoticeDTO {
    private String description;
    // 图片链接
    private String img;
    private String shopName;
    private BigDecimal price;
    private BigDecimal targetPrice;
    private String detailUrl;
}
