package com.intela.cryptoservice.controllers;

import com.binance.api.client.domain.market.Candlestick;
import com.intela.cryptoservice.models.CandleStick;
import com.intela.cryptoservice.services.BinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
public class CryptoController {
    private final BinanceService binanceService;
    @GetMapping("/")
    public ResponseEntity<List<CandleStick>> fetchAllCryptoCurrencies() {
        return ResponseEntity.ok()
                .body(binanceService.fetchLatestCandleSticks());
    }

    //Get crypto by code
    @GetMapping("/{symbol}")
    public ResponseEntity<Candlestick> fetchCryptoByCode(
            @PathVariable String symbol
    ) {
        return ResponseEntity.ok()
                .body(this.binanceService.fetchBySymbol(symbol));
    }

    //Predict crypto
    @GetMapping("/predict/{symbol}")
    public ResponseEntity<Long> predict(
            @PathVariable String symbol
    ) {
        return ResponseEntity.ok()
                .body(this.binanceService.predict(symbol));
    }
}
