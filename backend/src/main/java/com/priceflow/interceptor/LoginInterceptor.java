package com.priceflow.interceptor;

import com.priceflow.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 33954
 * #Description LoginInterceptor
 * #Date: 2025/3/30 17:20
 */
public class LoginInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = UserHolder.getUser();
        if (userId == null) {
            response.setStatus(401);
            String url = request.getRequestURI();
            logger.info(url+":用户未登录");
            return false;
        } else {
            return true;
        }
    }
}
