package org.example.springbootloginregisterdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.springbootloginregisterdemo.mapper")
public class SpringbootLoginRegisterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLoginRegisterDemoApplication.class, args);
    }

}
