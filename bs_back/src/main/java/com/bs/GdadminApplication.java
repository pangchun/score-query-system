package com.bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GdadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdadminApplication.class, args);
    }

}
