package cz.spitsoft.testcrowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(proxyBeanMethods = false, exclude = {SecurityAutoConfiguration.class})
@EnableWebSecurity
@Configuration
public class TestcrowdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestcrowdApplication.class, args);
    }

}
