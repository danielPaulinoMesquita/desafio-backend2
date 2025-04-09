package br.com.daniel.testerevenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class TesteRevendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteRevendaApplication.class, args);
    }

}
