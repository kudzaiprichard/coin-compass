package com.intela.cryptoservice.models;

import com.binance.api.client.domain.market.Candlestick;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandleStick extends Candlestick {
    private String Symbol;
}
