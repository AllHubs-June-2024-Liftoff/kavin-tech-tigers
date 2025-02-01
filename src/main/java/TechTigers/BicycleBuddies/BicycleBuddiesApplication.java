package TechTigers.BicycleBuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BicycleBuddiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BicycleBuddiesApplication.class, args);
    }

}
