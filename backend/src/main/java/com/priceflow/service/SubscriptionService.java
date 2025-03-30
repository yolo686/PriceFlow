package com.priceflow.service;

import com.priceflow.mapper.SubscriptionMapper;
import com.priceflow.pojo.entity.Good;
import com.priceflow.pojo.entity.Subscription;
import com.priceflow.pojo.vo.Result;
import com.priceflow.utils.Crawler;
import com.priceflow.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 33954
 * #Description SubscriptionService
 * #Date: 2025/3/30 19:06
 */

//public class Subscription {
//    private Integer id;
//    private Integer userId;
//    private String description;
//    private String img;
//    private String shopName;
//    private BigDecimal price;
//    private String detailUrl;
//}
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    public Result addSubscription(Good good) {
        Subscription subscription = transform(good);
        subscriptionMapper.insert(subscription);
        return Result.success();
    }

    private Subscription transform(Good good) {
        Subscription subscription = new Subscription();
        subscription.setUserId(UserHolder.getUser());
        subscription.setDescription(good.getDescription());
        subscription.setImg(good.getImg());
        subscription.setShopName(good.getShopName());
        subscription.setPlatform(good.getPlatform());
        subscription.setPrice(good.getPrice());
        subscription.setDetailUrl(good.getDetailUrl());
        return subscription;
    }

    public Result cancelSubscription(Integer id) {
        subscriptionMapper.deleteById(id);
        return Result.success();
    }

    public Result checkSubscription(Good good) {
        Subscription subscription = transform(good);
        subscription = subscriptionMapper.selectOne(subscription);
        if(subscription == null) {
            return Result.success(false);
        } else {
            return Result.success(true);
        }
    }

    public Result<List<Subscription>> getSubscriptions() {
        List<Subscription> subscriptions = subscriptionMapper.selectList(UserHolder.getUser());
        Crawler.update(subscriptions);
        return Result.success(subscriptions);
    }
}
