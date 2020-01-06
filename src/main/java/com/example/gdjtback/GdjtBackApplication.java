package com.example.gdjtback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gdjtback.mapper")
public class GdjtBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdjtBackApplication.class, args);
    }

}
