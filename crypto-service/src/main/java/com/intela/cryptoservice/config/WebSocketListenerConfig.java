package com.intela.cryptoservice.config;

import com.intela.cryptoservice.services.BinanceService;
import okhttp3.WebSocketListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketListenerConfig {

    @Bean
    public WebSocketListener webSocketListener(BinanceService binanceService) {
        return new MyWebSocketListener(binanceService, "symbol");
    }
}