package cz.brich.memsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class MemsourceServiceApplication {

    public static void main(final String... args) {
        SpringApplication.run(MemsourceServiceApplication.class, args);
    }
}
