package com.lixing.siitep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lixing.siitep.mapper")
public class SiitepApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiitepApplication.class, args);
    }

}
