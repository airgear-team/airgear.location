package com.airgear.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "com.airgear")
public class AirGearLocalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirGearLocalApplication.class, args);
    }

}
