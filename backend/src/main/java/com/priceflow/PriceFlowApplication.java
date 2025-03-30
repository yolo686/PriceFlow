package com.priceflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.priceflow.mapper")
public class PriceFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceFlowApplication.class, args);
    }

}
