package com.priceflow.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 33954
 * #Description DruidDataSourceConfig
 * #Date: 2025/3/30 12:00
 */


@Configuration
public class DruidDataSourceConfig {

    @ConfigurationProperties(value = "spring.datasource")  // 读取 类路径下的application.yaml
    // 的信息，并为下面的 对应的 setXX 进行赋值操作
    @Bean
    public DataSource dataSource() {

        return new DruidDataSource();

    }

}
