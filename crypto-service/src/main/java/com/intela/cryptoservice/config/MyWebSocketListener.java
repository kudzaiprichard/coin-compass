package com.intela.cryptoservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intela.cryptoservice.models.CandleStick;
import com.intela.cryptoservice.services.BinanceService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
public class MyWebSocketListener extends WebSocketListener {

    private final BinanceService binanceService;
    private final String symbol;

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        System.out.println("Opened websocket connection for symbol");
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String json) {
        try {
            // Parse JSON message
            CandleStick candlestick = new ObjectMapper().readValue(json, CandleStick.class);
            binanceService.handleCandleStickData(candlestick);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing websocket message for symbol " + e);
        }
    }
}
