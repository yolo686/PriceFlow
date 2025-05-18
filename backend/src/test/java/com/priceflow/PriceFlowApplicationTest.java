package com.priceflow;

import com.priceflow.mapper.UserMapper;
import com.priceflow.utils.AIQuery;
import com.priceflow.utils.DatabaseScanner;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.CompletableFuture;

import static com.priceflow.utils.AIQuery.queryAsync;

/**
 * @author 33954
 * #Description PriceFlowApplicationTest
 * #Date: 2025/3/30 12:01
 */
@SpringBootTest
class PriceFlowApplicationTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DatabaseScanner databaseScanner;


    @Autowired
    private ApplicationContext context;

    public void checkBean() {
        boolean exists = context.containsBean("userMapper");
        System.out.println("UserMapper exists: " + exists);
    }

    @Test
    public void t1() {
        // 输出看看当前的数据源是什么
        System.out.println(jdbcTemplate.getDataSource().getClass());

    }

    @Test
    public void t2() {
        // 测试Mapper
//        userMapper.insertUser();
        databaseScanner.scan();
    }

    @Test
    public void t3() throws Exception {
        // 调用异步方法
        CompletableFuture<String> future = queryAsync("送给女朋友的生日礼物推荐");

        // 阻塞等待结果（不推荐在生产环境长时间阻塞）
        String advice = future.join(); // 或使用 future.get()
        System.out.println("AI建议: " + advice);
    }

}