package com.example.demo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Communication {
    private static final String URL = "http://94.198.50.185:7081/api/users/";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static List<String> cookies;

    public static ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange
                (URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        cookies = new ArrayList<>(Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie")));
        return responseEntity;
    }

    public static ResponseEntity<String> postUser(User user) {
        return restTemplate.postForEntity(URL, getHttpRequest(user), String.class);
    }

    public static ResponseEntity<String> updateUser(User user) {
        return restTemplate.exchange(URL, HttpMethod.PUT, getHttpRequest(user), String.class);
    }

    public static ResponseEntity<String> deleteUser(User user) {
        return restTemplate.exchange(URL + user.getId(), HttpMethod.DELETE, getHttpRequest(user), String.class);
    }

    public static HttpEntity<User> getHttpRequest(User user) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", String.join(";", cookies));
        requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(user, requestHeaders);
    }
}
