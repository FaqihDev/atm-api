package com.jamsirat.atmapi.config;

import com.jamsirat.atmapi.service.impl.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorConfig {

    @Bean(name = "auditorProvider")
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }

}
