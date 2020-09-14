package de.juplo.boot.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;


@SpringBootApplication
public class Application {

    private final static Logger LOG = LoggerFactory.getLogger(Application.class);


    @Bean
    public Clock clock() {
      return Clock.systemDefaultZone();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
