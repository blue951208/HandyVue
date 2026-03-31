package org.handy.handyvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.handy.handyvue.mapper")
public class HandyVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandyVueApplication.class, args);
    }

}
