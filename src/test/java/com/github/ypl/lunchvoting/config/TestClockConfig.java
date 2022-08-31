package com.github.ypl.lunchvoting.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

@TestConfiguration
public class TestClockConfig {

    @Bean
    public Clock voteClock() {
        return Clock.fixed(Instant.parse(LocalDate.now() + "T12:00:00Z"), Clock.systemUTC().getZone());
    }
}
