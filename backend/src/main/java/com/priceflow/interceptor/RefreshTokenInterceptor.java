package com.priceflow.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aopalliance.intercept.Interceptor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import com.priceflow.utils.UserHolder;
import java.util.concurrent.TimeUnit;

/**
 * @author 33954
 * #Description RefreshTokenInterceptor
 * #Date: 2025/3/30 17:20
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;
    private String token;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("authorization");
        UserHolder.setUser(2);
        if (StrUtil.isBlank(token)) {
            return true;
        }
        // 基于TOKEN获取redis中的用户
        String key  = token;
        this.token = token;
        String userId = stringRedisTemplate.opsForValue().get(key);
        // 判断用户是否存在
        if (userId == null) {
            return true;
        }
        // 存在，保存用户信息到 ThreadLocal
        UserHolder.setUser(Integer.valueOf(userId));
        // 刷新token有效期
        stringRedisTemplate.expire(key, 4, TimeUnit.HOURS);
        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        Integer userId = UserHolder.getUser();
        if(userId != null &&  userId < 0) {
            stringRedisTemplate.delete(token);
        }
        UserHolder.removeUser();
    }
}
