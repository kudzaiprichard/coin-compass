package com.intela.cryptoservice.services;

import com.binance.api.client.domain.market.CandlestickInterval;
import com.intela.cryptoservice.config.Config;
import com.intela.cryptoservice.config.MyWebSocketListener;
import com.intela.cryptoservice.models.CandleStick;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BinanceService {

    private final OkHttpClient client;
    private final Config config;
    private final RestClient restClient = RestClient.create();

    private final List<CandleStick> candlestickList = new ArrayList<>();

    public void connectAndSubscribe() {
        for (String symbol : this.config.symbols()) {
            String url = String.format("wss://stream.binance.com:9443/ws/%s@kline_%s", symbol, CandlestickInterval.DAILY);
            try {
                WebSocket webSocket = client.newWebSocket(new Request.Builder().url(url).build(), new MyWebSocketListener(this, symbol));
                webSocket.wait(1000);
            } catch (Exception e) {
                throw new RuntimeException("Error connecting to websocket for symbol " + symbol, e);
            }
        }
    }

    public void handleCandleStickData(CandleStick candlestickData) {
        this.candlestickList.add(candlestickData);
    }

    public List<CandleStick> fetchLatestCandleSticks() {
        this.connectAndSubscribe();

        if(!candlestickList.isEmpty()){
           return candlestickList;
        }

        throw new RuntimeException("Failed to fetch candle stick , Candle stick List empty");
    }

    public CandleStick fetchBySymbol(String symbol) {
        this.connectAndSubscribe();

        for(CandleStick candlestick : candlestickList){
            if(Objects.equals(candlestick.getSymbol(), symbol)){
                return candlestick;
            }
        }
        throw new RuntimeException("Failed to fetch candlestick symbol");
    }

    public Long predict(String symbol) {
        String uri = ""; //Todo: add predict-service uri
        var candle = this.fetchBySymbol(symbol);

        //Should return prediction score
        return this.restClient
                .post()
                .uri(uri)
                .body(candle)
                .retrieve()
                .body(Long.class);
    }
}