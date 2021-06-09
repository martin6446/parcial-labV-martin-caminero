package com.example.parcial.martin.caminero.api;

import com.example.parcial.martin.caminero.model.Dollar;
import com.example.parcial.martin.caminero.model.Euro;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiCallService {

    @CircuitBreaker(name = "euros",fallbackMethod = "fallback")
    public static Euro getEuros() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .build();
        
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body().substring(10,response.body().length()-2), Euro.class);
    }


    @CircuitBreaker(name = "dollar",fallbackMethod = "fallback")
    public static Dollar getDollars() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body().replace("[","").replace("]",""), Dollar.class);
    }

    public Euro fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return Euro.builder().build();
    }

}
