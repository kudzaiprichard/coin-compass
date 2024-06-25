package com.intela.cryptoservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PredictionResponse {
    @JsonProperty("Prediction")
    private Double prediction;
}
