package com.priceflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceFlowApplication.class, args);
    }

}
