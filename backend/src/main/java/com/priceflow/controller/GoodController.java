package com.priceflow.controller;

import com.priceflow.pojo.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 33954
 * #Description GoodController
 * #Date: 2025/3/29 20:22
 */
@RequestMapping("/good")
@RestController
@Tag(name = "商品模块", description = "支持用户查询商品价格，询问AI建议，查看历史搜索等")
public class GoodController {
    @Operation(summary = "商品搜索", description = "根据关键字搜索商品,自由选择拼多多、淘宝、京东等多个平台")
    @ApiResponse(responseCode = "200", description = "搜索结果")
    @GetMapping(value = "/search")
    public Result searchProducts() {
        // 实现流式响应逻辑
        return Result.success();
    }

    @Operation(summary = "AI购买建议", description = "询问AI获取商品购买建议")
    @ApiResponse(responseCode = "200", description = "建议内容",
            content = @Content(mediaType = "text/event-stream"))
    @GetMapping(value = "/ai")
    public Result getAiAdvice() {
        // 实现流式响应逻辑
        return Result.success();
    }


    @Operation(summary = "历史搜索记录", description = "获取用户的历史搜索记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "历史价格列表"),
            @ApiResponse(responseCode = "401", description = "用户不存在")
    })
    @GetMapping(value = "/history")
    public Result getGoodHistory() {
        // 实现历史价格查询逻辑
        return Result.success();
    }
}
