package com.switchfully.digibooky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DigibookyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigibookyApplication.class, args);
    }

}
