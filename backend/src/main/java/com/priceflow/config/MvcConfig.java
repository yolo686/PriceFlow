package com.priceflow.config;

import com.priceflow.interceptor.LoginInterceptor;
import com.priceflow.interceptor.RefreshTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 33954
 * #Description MvcConfig
 * #Date: 2025/3/30 17:36
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/subscription/**",
                        "/notice/**",
                        "/good/**").
                order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).
                addPathPatterns("/**").
                order(0);
    }
}
