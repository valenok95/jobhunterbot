package ru.wallentos.jobhunterbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.wallentos.jobhunterbot.config.BotConfig;

@SpringBootApplication
public class JobhunterbotApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobhunterbotApplication.class, args);
    }
}
