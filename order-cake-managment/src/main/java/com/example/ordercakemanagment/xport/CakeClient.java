package com.example.ordercakemanagment.xport;

import com.example.ordercakemanagment.domain.valueobjects.Cake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class CakeClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public CakeClient(@Value("${app.product-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Cake> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/cakes").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Cake>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
