package com.priceflow.service;

import com.priceflow.mapper.GoodMapper;
import com.priceflow.mapper.SubscriptionMapper;
import com.priceflow.pojo.entity.Good;
import com.priceflow.pojo.entity.History;
import com.priceflow.pojo.entity.Subscription;
import com.priceflow.pojo.vo.GoodVO;
import com.priceflow.pojo.vo.Result;
import com.priceflow.utils.AIQuery;
import com.priceflow.utils.Crawler;
import com.priceflow.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.priceflow.utils.AIQuery.queryAsync;


/**
 * @author 33954
 * #Description GoodService
 * #Date: 2025/3/30 19:07
 */
@Service
public class GoodService {

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private GoodMapper goodMapper;

    public Result queryAiAdvice(String query) {
        String advice = null;
        try {
            // 调用异步方法
            CompletableFuture<String> future = queryAsync(query);

            // 阻塞等待结果（不推荐在生产环境长时间阻塞）
            advice = future.join(); // 或使用 future.get()
        } catch (Exception e) {
            return Result.fail(500,"查询失败");
        }
        if(advice == null) {
            return Result.fail(500,"查询失败");
        }
        return Result.success(advice);
    }

    public Result<List<GoodVO>> search(String content,int platform) {
        List<Good> goods = Crawler.crawl(content, platform);
        if(goods == null || goods.isEmpty()) {
            return Result.fail(500,"查询失败");
        }
        List<GoodVO> goodVOS = new LinkedList<>();
        for(Good good : goods) {
            GoodVO goodVO = new GoodVO(good);
            Subscription subscription = SubscriptionService.transform(good);
            subscription = subscriptionMapper.selectOne(subscription);
            goodVO.setSubscribed(subscription != null);
            goodVOS.add(goodVO);
        }
        History history = new History();
        history.setUserId(UserHolder.getUser());
        history.setContent(content);
        goodMapper.insert(history);
        return Result.success(goodVOS);
    }

    public Result<List<History>> getGoodHistory() {
        return Result.success(goodMapper.selectByUserId(UserHolder.getUser()));
    }
}
