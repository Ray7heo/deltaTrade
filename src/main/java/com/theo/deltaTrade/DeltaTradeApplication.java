package com.theo.deltaTrade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.theo.deltaTrade.mapper")
public class DeltaTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeltaTradeApplication.class, args);
    }

}
