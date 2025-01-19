package com.report.Reporting.Service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerServiceClient {

    private final RestTemplate restTemplate;


    public boolean validateToken(String jwt) {
        String url = "http://localhost:7777/api/auth/validate-token"; // URL of the validation endpoint in customer-service
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
                new org.springframework.http.HttpEntity<>(headers), String.class);

        return response.getStatusCode().is2xxSuccessful();
    }
}
