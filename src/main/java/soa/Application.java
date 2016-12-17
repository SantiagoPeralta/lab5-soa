package soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// Para limitar el numero de twits
/*
limit => para limitar el numero de twits por paguina
numberOfPages => para limitar a 1 paguina
con lo cual limit sera n y numberOfPages = 1
 */