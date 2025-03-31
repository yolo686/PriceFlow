package com.priceflow.utils;


import com.priceflow.mapper.NoticeMapper;
import com.priceflow.mapper.UserMapper;
import com.priceflow.pojo.entity.Notice;
import com.priceflow.pojo.entity.Subscription;
import com.priceflow.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 33954
 * #Description DatabaseScanner
 * #Date: 2025/3/31 14:51
 */
@Component
public class DatabaseScanner {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;

    // 创建一个线程池，用于爬虫任务
    private final ThreadPoolExecutor pools = new ThreadPoolExecutor( 0, 5, 600L, TimeUnit.SECONDS, new SynchronousQueue<>());

    // 任务类
    class Task implements Runnable {
        private Notice notice;

        public Task(Notice notice) {
            this.notice = notice;
        }

        @Override
        public void run() {
            BigDecimal price = Crawler.updatePrice(notice);
            if(price != null && price.compareTo(notice.getPresentPrice()) != 0){
                notice.setPresentPrice(price);
                // 当前价格低于预期价格发送邮件
                if(price.compareTo(notice.getTargetPrice()) <= 0){
//                    notice.setIsNotice(true);
                    User user = new User();
                    user.setId(notice.getUserId());
                    user = userMapper.select(user);
                    Email.remind(user.getEmail(),notice);
                }
                noticeMapper.update(notice);
            }
        }
    }

    public void scan() {
        int id = 0;
        while (true) {
            List<Notice> notices = noticeMapper.selectBatch(id);
            if(notices==null || notices.isEmpty()) break;
            id = notices.get(notices.size() - 1).getId();
            for(Notice notice : notices){
                pools.execute(new Task(notice));
                System.out.println(notice.toString());
            }
            if(notices.size() < 10) {
                break;
            }

        }
    }
}

