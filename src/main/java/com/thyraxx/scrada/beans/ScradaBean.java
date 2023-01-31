package com.thyraxx.scrada.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Configuration
public class ScradaBean {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}
