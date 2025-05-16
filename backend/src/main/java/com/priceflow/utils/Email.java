package com.priceflow.utils;

import com.priceflow.pojo.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static org.springframework.http.HttpHeaders.FROM;

/**
 * @author 33954
 * #Description Email
 * #Date: 2025/3/30 15:28
 */
@Component
public class Email {

    // 邮箱地址的正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static JavaMailSender mailSender;

    @Autowired
    public Email(JavaMailSender javaMailSender) {
        Email.mailSender = javaMailSender;
    }

    // 对邮箱地址进行简单的合法校验
    public static boolean check(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }


    // 发送验证码到邮箱
    //TODO:没测试
    public static boolean send(String email, String code) {
        if (!check(email)) return false;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM);
            message.setTo(email);
            message.setSubject("【PriceFlow 验证码】");
            message.setText("您的验证码是：" + code + "，请在5分钟内使用。");
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 可替换为日志记录
            return false;
        }
    }

    // 发送降价通知到用户邮箱
    //TODO:没进行测试
    public static boolean remind(String email, Notice notice) {
        if (!check(email)) return false;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM);
            message.setTo(email);
            message.setSubject("【PriceFlow 降价提醒】");

            String content = String.format("""
                    您订阅的商品降价啦！
                    
                    商品描述：%s
                    当前价格：¥%s
                    您设置的目标价格：¥%s
                    
                    商品链接：%s
                    
                    请及时查看抢购！
                    """,
                    notice.getDescription(),
                    notice.getPresentPrice(),
                    notice.getTargetPrice(),
                    notice.getDetailUrl()
            );

            message.setText(content);
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
