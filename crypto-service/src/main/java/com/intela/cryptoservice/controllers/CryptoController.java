package com.intela.cryptoservice.controllers;

import com.intela.cryptoservice.models.CandleStick;
import com.intela.cryptoservice.services.BinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final BinanceService binanceService;

    @GetMapping("/candlesticks")
    public List<CandleStick> fetchAllCryptoCurrencies() {
        return binanceService.fetchLatestCandleSticks();
    }

    @GetMapping("/candlestick")
    public CandleStick fetchCandlestickBySymbol(@RequestParam String symbol) {
        return binanceService.fetchBySymbol(symbol);
    }

    @GetMapping("/predict")
    public Double predictBySymbol(@RequestParam String symbol) {
        return binanceService.predict(symbol);
    }
}
