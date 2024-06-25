package com.intela.cryptoservice.services;

import com.intela.cryptoservice.client.PredictClient;
import com.intela.cryptoservice.models.CandleStick;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BinanceService {

    private final PredictClient predictClient;
    private final RestTemplate restTemplate;
    private final List<CandleStick> candlestickList = new ArrayList<>();

    public void fetchAndStoreCandlestickData() {
        String[] symbols = {"bitcoin", "ethereum"};
        String days = "1";

        for (String symbol : symbols) {
            String url = String.format("https://api.coingecko.com/api/v3/coins/%s/market_chart?vs_currency=usd&days=%s", symbol, days);
            Map<String, Object> data = restTemplate.getForObject(url, Map.class);

            if (data != null && data.containsKey("prices")) {
                List<List<Object>> prices = (List<List<Object>>) data.get("prices");

                // Only take the first price data for simplicity
                if (!prices.isEmpty()) {
                    List<Object> price = prices.get(0); // Get the first entry

                    CandleStick candlestick = new CandleStick();
                    candlestick.setSymbol(symbol);

                    // Extracting values from the price list
                    Double timestamp = ((Number) price.get(0)).doubleValue();
                    Double priceValue = ((Number) price.get(1)).doubleValue();

                    candlestick.setOpen_price(priceValue); // Assuming the price array has [timestamp, price]
                    candlestick.setClose_price(priceValue); // Adjust as per your data structure

                    // You need to parse the other values from the API response accordingly
                    candlestick.setHigh_price(0.0); // Replace with actual high price logic
                    candlestick.setLow_price(0.0); // Replace with actual low price logic
                    candlestick.setVolume(0.0); // Replace with actual volume logic

                    candlestickList.add(candlestick);
                }
            }
        }
    }

    public List<CandleStick> fetchLatestCandleSticks() {
        this.fetchAndStoreCandlestickData();

        if (!candlestickList.isEmpty()) {
            return candlestickList;
        }

        throw new RuntimeException("Failed to fetch candlesticks, Candlestick List empty");
    }

    public CandleStick fetchBySymbol(String symbol) {
        this.fetchAndStoreCandlestickData();

        for (CandleStick candlestick : candlestickList) {
            if (symbol.equalsIgnoreCase(candlestick.getSymbol())) {
                return candlestick;
            }
        }
        throw new RuntimeException("Failed to fetch candlestick symbol: " + symbol);
    }

    public Double predict(String symbol) {
        CandleStick candlestick = this.fetchBySymbol(symbol);
        return predictClient.predict(candlestick);
    }
}
