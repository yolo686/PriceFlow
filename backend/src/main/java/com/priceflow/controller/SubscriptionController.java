package com.priceflow.controller;

import com.priceflow.pojo.entity.Good;
import com.priceflow.pojo.entity.Subscription;
import com.priceflow.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.priceflow.pojo.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * @author 33954
 * #Description SubscriptionController
 * #Date: 2025/3/29 20:22
 */
@RestController
@RequestMapping("/subscription")
@Tag(name = "订阅管理模块", description = "商品订阅相关操作")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * 添加商品订阅
     * @return
     */
    @Operation(summary = "添加商品订阅", description = "订阅指定商品")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "订阅成功"),
            @ApiResponse(responseCode = "400", description = "无效请求")
    })
    @PostMapping(value = "/add")
    public Result addSubscription(@RequestBody Good good) {
        return subscriptionService.addSubscription(good);
    }

    /**
     * 取消商品订阅
     * @return
     */
    @Operation(summary = "取消商品订阅", description = "取消订阅指定商品")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "取消成功"),
            @ApiResponse(responseCode = "404", description = "未找到订阅记录")
    })
    @DeleteMapping(value = "/cancel")
    public Result cancelSubscription(Integer id) {
        return subscriptionService.cancelSubscription(id);
    }

    /**
     * 获取用户所有订阅
     * @return
     */
    @Operation(summary = "获取用户所有订阅", description = "获取当前用户订阅的所有商品")
    @ApiResponse(responseCode = "200", description = "订阅列表")
    @GetMapping("/list")
    public Result<List<Subscription>> getSubscriptions() {
        return subscriptionService.getSubscriptions();
    }

    /**
     * 检查商品订阅状态
     * @return
     */
    @Operation(summary = "检查商品订阅状态", description = "检查当前用户是否订阅了指定商品")
    @ApiResponse(responseCode = "200", description = "订阅状态")
    @PostMapping(value = "/check")
    public Result checkSubscription(@RequestBody Good good) {
        return subscriptionService.checkSubscription(good);
    }

}
