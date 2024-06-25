package com.intela.cryptoservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class CoinGeckoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getCandlestickData(String id, String days, String interval) {
        String url = String.format("https://api.coingecko.com/api/v3/coins/%s/market_chart?vs_currency=usd&days=%s&interval=%s", id, days, interval);
        return restTemplate.getForObject(url, Map.class);
    }
}
