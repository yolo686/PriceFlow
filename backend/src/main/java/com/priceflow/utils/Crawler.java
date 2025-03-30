package com.priceflow.utils;

import com.priceflow.pojo.entity.Good;
import com.priceflow.pojo.entity.Subscription;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 33954
 * #Description Crawler
 * #Date: 2025/3/30 17:05
 */
public class Crawler {
    /**
     * 爬取商品信息
     * @param content，搜索的信息
     * @param type：后三位代表数据来源，1表示搜索，0表示不从该网站爬取数据
     *             右边第1位：淘宝
     *             右边第2位：京东
     *             右边第3位：拼多多
     *            如101：表示从拼多多和淘宝获取数据
     * @return
     */
    public static List<Good> crawl(String content,int type) {
        // todo
        // 模拟爬取数据
        List<Good> goods = new ArrayList<>();
        Good good1 = new Good();
        good1.setImg("img-url");
        good1.setDescription("good");
        good1.setPrice(new BigDecimal("100"));
        good1.setDetailUrl("url");
        goods.add(good1);
        return goods;
    }

    // 更新订阅商品的价格，将商品的价格重新再爬取一遍
    public static void update(List<Subscription> subscriptions) {
        // todo
    }
}
