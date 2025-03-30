package com.priceflow.controller;

import com.priceflow.pojo.dto.EmailLogin;
import com.priceflow.pojo.dto.PasswordLogin;
import com.priceflow.pojo.dto.UserInfo;
import com.priceflow.pojo.vo.Result;
import com.priceflow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userInfo
     */
    @Operation(summary = "用户注册", description = "创建新用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户创建成功"),
            @ApiResponse(responseCode = "401", description = "用户已存在")
    })
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserInfo userInfo) {
        // 实现注册逻辑
        return userService.register(userInfo);
    }

    /**
     * 密码登录
     * @param passwordLogin
     */
    @Operation(summary = "密码登录", description = "输入用户名和密码," +
            "登录成功后会返回一个token，放在Result的data字段中，保存这个token，下次前端发起请求时在authorization首部字段存放token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "认证失败")
    })
    @PostMapping(value = "/loginWithPassword")
    public Result loginWithPassword(@RequestBody PasswordLogin passwordLogin) {
        // 实现登录逻辑
        return userService.loginWithPassword(passwordLogin);
    }

    /**
     * 验证码登录
     * @Param passwordLogin
     * @return
     */
    @Operation(summary = "验证码登录", description = "输入邮箱和验证码，" +
            "登录成功后会返回一个token，放在Result的data字段中，保存这个token，下次前端发起请求时在authorization首部字段存放token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "认证失败")
    })
    @PostMapping(value = "/loginWithCode")
    public Result loginWithCode(@RequestBody EmailLogin emailLogin) {
        // 实现登录逻辑
        return userService.loginWithCode(emailLogin);
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @Operation(summary = "验证码发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "发送成功"),
            @ApiResponse(responseCode = "400", description = "发送失败")
    })
    @PostMapping("/sendCode")
    public Result sendCode(String email) {
        return userService.sendCode(email);
    }

    /**
     * 用户登出
     * @return
     */
    @Operation(summary = "用户登出", description = "登出当前用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登出成功"),
            @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PostMapping("/logout")
    public Result logout() {
        // 实现登出逻辑
        return userService.logout();
    }
}
