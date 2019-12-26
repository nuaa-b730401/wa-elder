package org.nuaa.wa.waelder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WaElderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaElderApplication.class, args);
    }

}
