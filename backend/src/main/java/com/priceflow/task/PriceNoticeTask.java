package com.priceflow.task;

import com.priceflow.utils.DatabaseScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author 33954
 * #Description PriceNoticeTask
 * #Date: 2025/3/31 16:33
 */
@Configuration
public class PriceNoticeTask {

    @Autowired
    private DatabaseScanner databaseScanner;

    // 十分钟扫描一次
    @Scheduled(cron = "0 */10 * * * ?")
    public void priceNoticeTask(){
        System.out.println("定时任务开始执行");
        databaseScanner.scan();
    }
}
