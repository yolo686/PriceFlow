package com.priceflow.service;

import cn.hutool.core.util.RandomUtil;
import com.priceflow.mapper.UserMapper;
import com.priceflow.pojo.dto.EmailLogin;
import com.priceflow.pojo.dto.PasswordLogin;
import com.priceflow.pojo.dto.UserInfo;
import com.priceflow.pojo.entity.User;
import com.priceflow.pojo.vo.Result;
import com.priceflow.utils.Email;
import com.priceflow.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author 33954
 * #Description UserService
 * #Date: 2025/3/30 15:10
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Result loginWithPassword(PasswordLogin passwordLogin) {
        User user = new User();
        user.setUsername(passwordLogin.getUsername());
        // md5哈希
        String password = DigestUtils.md5DigestAsHex(passwordLogin.getPassword().getBytes());
        user.setPassword(password);
        user = userMapper.select(user);
        if(user == null) {
            return Result.fail(401,"用户名或密码错误");
        }
        return generateToken(user.getId());
    }

    public Result generateToken(Integer id) {
        // 生成token:id + UUID
        String token = id + "&" + RandomUtil.randomString(32);
        // 保存token到redis
        stringRedisTemplate.opsForValue().set(token,id.toString(),4, TimeUnit.HOURS);
        return Result.success(token);
    }

    public Result sendCode(String email) {
        // 对邮箱进行校验
        if(!Email.check(email)) {
            return Result.fail(400,"邮箱格式错误");
        }
        // 生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 保存验证码
        stringRedisTemplate.opsForValue().set(email,code,60, TimeUnit.SECONDS);
        // 发送验证码
        if(Email.send(email,code)) {
            return Result.success("验证码已发送");
        } else {
            return Result.fail(500,"验证码发送失败");
        }
    }

    public Result register(UserInfo userInfo) {
        String email = userInfo.getEmail();
        String code = userInfo.getCode();
        // 校验验证码
        String checkCode = stringRedisTemplate.opsForValue().get(email);
        if(checkCode == null || !checkCode.equals(code)) {
            return Result.fail(400,"验证码错误或已失效");
        }
        User user = new User();
        user.setUsername(userInfo.getUsername());
        // MD5加密
        String passswordHash = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
        user.setPassword(passswordHash);
        user.setEmail(email);
        try {
            int result = userMapper.insert(user);
            if (result > 0) {
                return Result.success();
            } else {
                return Result.fail(500,"注册失败");
            }
        } catch (DuplicateKeyException e) {
            // 处理重复键异常
            String errorMessage = e.getMessage();
            if (errorMessage.contains("username")) {
                return Result.fail(400,"注册失败，用户名已存在");
            } else if (errorMessage.contains("email")) {
                return Result.fail(400,"注册失败，邮箱已注册");
            } else {
                return Result.fail(400,"注册失败");
            }
        }
    }

    public Result loginWithCode(EmailLogin emailLogin) {
        String email = emailLogin.getEmail();
        String code = emailLogin.getCode();
        // 校验验证码
        String checkCode = stringRedisTemplate.opsForValue().get(email);
        if(checkCode == null || !checkCode.equals(code)) {
            return Result.fail(400,"验证码错误或已失效");
        }
        User user = new User();
        user.setEmail(email);
        user = userMapper.select(user);
        if(user == null) {
            return Result.fail(401,"该邮箱未注册！");
        }
        return generateToken(user.getId());
    }

    public Result logout() {
        UserHolder.setUser(-1);
        return Result.success();
    }
}
