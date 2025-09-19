package ru.netology.hwconditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.hwconditionalapp.DevProfile;
import ru.netology.hwconditionalapp.ProductionProfile;
import ru.netology.hwconditionalapp.SystemProfile;

@Configuration

public class JavaConfig {
    @Bean
    @ConditionalOnProperty(name ="netology.profile.dev",havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name ="netology.profile.dev",havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

}
