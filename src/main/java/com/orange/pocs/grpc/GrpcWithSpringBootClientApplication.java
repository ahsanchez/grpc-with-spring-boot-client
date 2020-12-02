package com.orange.pocs.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@SpringBootApplication
public class GrpcWithSpringBootClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcWithSpringBootClientApplication.class, args);
    }

    @Bean
    public RestTemplate getSuperRestTemplate() {
        return new RestTemplate();
    }

}
