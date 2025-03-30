package com.priceflow.utils;

import java.util.regex.Pattern;

/**
 * @author 33954
 * #Description Email
 * #Date: 2025/3/30 15:28
 */
public class Email {

    // 邮箱地址的正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // 对邮箱地址进行简单的合法校验
    public static boolean check(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean send(String email, String code) {
        System.out.println("send email to " + email + " with code " + code);
        return true;
    }
}
