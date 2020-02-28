package com.lixing.siitep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.lixing.siitep.mapper")
public class SiitepApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiitepApplication.class, args);
    }

}
