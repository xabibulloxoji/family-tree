package uz.sodiqdev.sajara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SajaraApplication {

    public static void main(String[] args) {
        SpringApplication.run(SajaraApplication.class, args);
    }

}
