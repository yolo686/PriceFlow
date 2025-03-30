package com.priceflow.utils;

/**
 * @author 33954
 * #Description UserHolder
 * #Date: 2025/3/30 17:18
 */
public class UserHolder {
    public static ThreadLocal<Integer> userContext = new ThreadLocal<>();
    public static void setUser(Integer userId) {
        userContext.set(userId);
    }
    public static Integer getUser() {
        return userContext.get();
    }
    public static void removeUser() {
        userContext.remove();
    }
}
