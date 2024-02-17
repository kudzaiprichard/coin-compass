package com.intela.cryptoservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Value("${application.binance.api.key}")
    private String apiKey;

    @Value("${application.binance.api.secret}")
    private String apiSecret;

    @Bean
    public List<String> symbols() {
        return Arrays.asList("BTCUSDT", "ETHUSDT");
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}