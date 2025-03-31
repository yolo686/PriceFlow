package com.priceflow.controller;

import com.priceflow.pojo.entity.Good;
import com.priceflow.pojo.entity.History;
import com.priceflow.pojo.vo.GoodVO;
import com.priceflow.pojo.vo.Result;
import com.priceflow.service.GoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 33954
 * #Description GoodController
 * #Date: 2025/3/29 20:22
 */
@RequestMapping("/good")
@RestController
@Tag(name = "商品模块", description = "支持用户查询商品价格，询问AI建议，查看历史搜索等")
public class GoodController {

    @Autowired
    private GoodService goodService;

    /**
     * 商品搜索
     * @return
     */
    @Operation(summary = "商品搜索", description = "根据关键字搜索商品,自由选择拼多多、淘宝、京东等多个平台")
    @ApiResponse(responseCode = "200", description = "搜索结果")
    @GetMapping(value = "/search")
    public Result<List<GoodVO>> searchProducts(@RequestParam String content, @RequestParam int platform) {
        return goodService.search(content,platform);
    }

    /**
     * AI购买建议
     * @param query
     * @return
     */
    @Operation(summary = "AI购买建议", description = "询问AI获取商品购买建议")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "建议内容"),
            @ApiResponse(responseCode = "500", description = "查询失败")
    })
    @GetMapping(value = "/ai")
    public Result getAiAdvice(String query) {
        // 实现流式响应逻辑
        return goodService.queryAiAdvice(query);
    }


    /**
     * 历史搜索记录
     * @return
     */
    @Operation(summary = "历史搜索记录", description = "获取用户的历史搜索记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "历史价格列表"),
            @ApiResponse(responseCode = "401", description = "用户不存在")
    })
    @GetMapping(value = "/history")
    public Result<List<History>> getGoodHistory() {
        // 实现历史价格查询逻辑
        return goodService.getGoodHistory();
    }
}
