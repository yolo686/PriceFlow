package com.priceflow.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author 33954
 * #Description History
 * #Date: 2025/3/30 10:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private Integer id;
    private Integer userId;
    private String content;
    private Date time;
}
