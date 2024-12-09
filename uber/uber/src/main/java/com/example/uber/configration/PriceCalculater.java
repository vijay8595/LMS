package com.example.uber.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@Configuration
public class PriceCalculater {


    @Bean
    public RestTemplate pricecalculator(){
        return new RestTemplate(getslowprice());
    }

    public ClientHttpRequestFactory getslowprice(){
        SimpleClientHttpRequestFactory smp=new SimpleClientHttpRequestFactory();
        smp.setConnectTimeout(5000);
        smp.setReadTimeout(5000);

        return smp;
}
}
