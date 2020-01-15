package cz.spitsoft.testcrowd;

import cz.spitsoft.testcrowd.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(proxyBeanMethods = false, exclude = {SecurityAutoConfiguration.class})
@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class TestcrowdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestcrowdApplication.class, args);
    }

}
