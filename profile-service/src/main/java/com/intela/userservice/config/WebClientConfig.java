package com.intela.userservice.config;

import com.intela.userservice.client.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final LoadBalancedExchangeFilterFunction filterFunction;
    @Bean
    public WebClient authWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://localhost:8081/")
                .filter(this.filterFunction)
                .build();
    }

    @Bean
    public AuthClient authClient(){
        HttpServiceProxyFactory  httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(authWebClient()))
                .build();
                //Todo: check if this work for .builder(WebClientAdapter.create(authWebClient())) deprecation

        return httpServiceProxyFactory.createClient(AuthClient.class);
    }
}
