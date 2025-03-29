package com.priceflow.controller;

import com.priceflow.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 33954
 * #Description UserController
 * #Date: 2025/3/17 14:24
 */
@RequestMapping("/user")
@RestController
@Tag(name = "用户模块", description = "支持用户注册、登录、退出，允许使用密码登录、验证码校验等多种登录方式，登录成功返回token")
public class UserController {


    @Operation(summary = "用户注册", description = "创建新用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户创建成功"),
            @ApiResponse(responseCode = "401", description = "用户已存在")
    })
    @PostMapping(value = "/register")
    public Result register() {
        // 实现注册逻辑
        return Result.success();
    }


    @Operation(summary = "密码登录", description = "输入用户名和密码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "认证失败")
    })
    @PostMapping(value = "/loginWithPassword")
    public Result loginWithPassword() {
        // 实现登录逻辑
        return Result.success();
    }

    @Operation(summary = "验证码登录", description = "输入邮箱和验证码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "认证失败")
    })
    @PostMapping(value = "/loginWithCode")
    public Result loginWithCode() {
        // 实现登录逻辑
        return Result.success();
    }

    @Operation(summary = "验证码发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "发送成功"),
            @ApiResponse(responseCode = "400", description = "发送失败")
    })
    @PostMapping("/sendCode")
    public Result<Void> sendCode() {
        // 实现登出逻辑
        return Result.success();
    }


    @Operation(summary = "用户登出", description = "登出当前用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登出成功"),
            @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PostMapping("/logout")
    public Result<Void> logout() {
        // 实现登出逻辑
        return Result.success();
    }
}
