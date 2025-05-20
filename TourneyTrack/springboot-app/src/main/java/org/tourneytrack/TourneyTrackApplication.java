package org.tourneytrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TourneyTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourneyTrackApplication.class, args);
    }

}
