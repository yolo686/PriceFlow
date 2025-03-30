package com.priceflow.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 33954
 * #Description Notice
 * #Date: 2025/3/30 10:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Integer id;
    private Integer userId;
    private String description;
    private String img;
    private String shopName;
    private BigDecimal presentPrice;
    private BigDecimal targetPrice;
    private String detailUrl;
    private Boolean isNotice;
}
