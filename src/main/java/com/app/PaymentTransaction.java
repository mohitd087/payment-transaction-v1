package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app")
public class PaymentTransaction
{

    public static void main(String[] args) {
        SpringApplication.run(PaymentTransaction.class, args);

    }
}
