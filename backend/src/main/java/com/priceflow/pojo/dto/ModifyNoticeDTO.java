package com.priceflow.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 33954
 * #Description ModifyNoticeDTO
 * #Date: 2025/3/30 21:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description= "修改降价通知的价格线")
public class ModifyNoticeDTO {
    private Integer id;
    private BigDecimal targetPrice;
}
