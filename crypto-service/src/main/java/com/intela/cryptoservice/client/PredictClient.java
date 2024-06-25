package com.intela.cryptoservice.client;

import com.intela.cryptoservice.models.CandleStick;
import com.intela.cryptoservice.models.PredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PredictClient {

    private final RestTemplate restTemplate;
    private final String uri = "http://127.0.0.1:5000/api/v1/predict/"; // Replace with your prediction service URI

    public Double predict(CandleStick candleStick) {
        try {
            ResponseEntity<PredictionResponse> response = restTemplate.postForEntity(uri, candleStick, PredictionResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("heelloooo" +response.getBody());
                PredictionResponse predictionResponse = response.getBody();
                if (predictionResponse != null && predictionResponse.getPrediction() != null) {
                    return predictionResponse.getPrediction();
                } else {
                    throw new RuntimeException("Prediction response does not contain valid data");
                }
            } else {
                throw new RuntimeException("Failed to predict using prediction service: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to predict using prediction service: " + e.getRawStatusCode() + " - " + e.getStatusText(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to predict using prediction service: " + e.getMessage(), e);
        }
    }
}
