package com.intela.cryptoservice.models;

import com.binance.api.client.domain.market.Candlestick;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandleStick {
    private String symbol;
    private Double open_price;
    private Double high_price;
    private Double low_price;
    private Double volume;
    private Double close_price;
}
