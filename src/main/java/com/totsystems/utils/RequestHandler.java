package com.totsystems.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler {

    public String securityFoundRequest(String secId){  //get XML security by secid
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity("http://iss.moex.com/iss/securities.xml?q=" + secId, String.class);
        return response.getBody().toString();
    };
}
