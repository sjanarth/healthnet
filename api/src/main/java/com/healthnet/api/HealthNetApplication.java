package com.healthnet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableCaching
//@EnableJpaAuditing // sugarsaas enables this by default
@SpringBootApplication(scanBasePackages={"com.sugarsaas", "com.healthnet"})
public class HealthNetApplication
{
    public static void main(String[] args)      {
        SpringApplication.run(HealthNetApplication.class, args);
    }
}