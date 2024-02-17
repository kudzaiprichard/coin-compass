package com.intela.cryptoservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Symbol {


    BTCUSDT("BTCUSDT"),
    LTCUSDT("LTCUSDT");

    private final String code;
}
