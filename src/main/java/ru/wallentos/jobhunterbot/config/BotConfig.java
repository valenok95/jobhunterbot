package ru.wallentos.jobhunterbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BotConfig {
  
@Bean
  public BotProps getBotProps() {
    System.out.println("initttt");
    return new BotProps();
  }

  @Data
  @Configuration
  @ConfigurationProperties("bot")
  public static class BotProps {
    private String name;
    private String key;
  }
}
