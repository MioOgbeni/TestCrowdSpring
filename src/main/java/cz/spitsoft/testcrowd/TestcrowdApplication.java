package cz.spitsoft.testcrowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(proxyBeanMethods = false, exclude = {SecurityAutoConfiguration.class})
public class TestcrowdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestcrowdApplication.class, args);
    }

}
