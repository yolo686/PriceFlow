package com.priceflow.controller;

import com.priceflow.pojo.dto.ModifyNoticeDTO;
import com.priceflow.pojo.dto.NoticeDTO;
import com.priceflow.pojo.entity.Notice;
import com.priceflow.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.priceflow.pojo.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 33954
 * #Description NoticeController
 * #Date: 2025/3/29 20:22
 */
@RestController
@RequestMapping("/notice")
@Tag(name = "降价通知管理模块", description = "设置降级通知、查看降价通知列表等相关操作")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 添加商品降价通知
     * @return
     */
    @Operation(summary = "添加商品降价通知", description = "订阅指定商品的价格变化通知")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "设置成功"),
            @ApiResponse(responseCode = "400", description = "无效请求")
    })
    @PostMapping(value = "/add")
    public Result addNotice(@RequestBody NoticeDTO noticeDTO) {
        return noticeService.addNotice(noticeDTO);
    }

    /**
     * 取消商品降价通知
     * @return
     */
    @Operation(summary = "取消商品降价通知", description = "取消指定商品的价格变化通知")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "取消成功"),
            @ApiResponse(responseCode = "404", description = "未找到降价通知商品")
    })
    @DeleteMapping(value = "/cancel")
    public Result cancelNotice(Integer id) {
        return noticeService.cancelNotice(id);
    }

    /**
     * 获取所以商品降价通知的商品
     * @return
     */
    @Operation(summary = "获取用户所有的降价通知", description = "获取当前用户订阅的降价通知的商品")
    @ApiResponse(responseCode = "200", description = "订阅列表")
    @GetMapping("/list")
    public Result<List<Notice>> getNotices() {
        return noticeService.getNotices();
    }

    /**
     * 修改商品降价提醒的金额
     * @return
     */
    @Operation(summary = "修改商品降价提醒的金额")
    @ApiResponse(responseCode = "200", description = "修改成功")
    @PostMapping(value = "/modify")
    public Result modifyNotice(@RequestBody ModifyNoticeDTO modifyNoticeDTO) {
        return noticeService.modifyNotice(modifyNoticeDTO.getId(),modifyNoticeDTO.getTargetPrice());
    }

}
