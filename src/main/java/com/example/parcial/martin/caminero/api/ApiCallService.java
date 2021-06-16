package com.example.parcial.martin.caminero.api;

import com.example.parcial.martin.caminero.model.Dollar;
import com.example.parcial.martin.caminero.model.Euro;
import com.example.parcial.martin.caminero.model.Player;
import com.example.parcial.martin.caminero.model.dto.StarPlayer;
import com.google.gson.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApiCallService {

    @CircuitBreaker(name = "euros",fallbackMethod = "fallback")
    public static Euro getEuros() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .header("Accept","application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return getEuro(response);
    }

    private static Euro getEuro(HttpResponse<String> response) {
        Euro euro = new Euro(JsonParser
                .parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("dolar")
                .getAsJsonObject()
                .get("nombre").getAsString(),
                JsonParser
                .parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("dolar")
                .getAsJsonObject()
                .get("compra").getAsString(),
                JsonParser
                .parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("dolar")
                .getAsJsonObject()
                .get("venta").getAsString());

        euro.setCompra(euro.getCompra().replace(",","."));
        euro.setVenta(euro.getVenta().replace(",","."));
        return euro;
    }


    @CircuitBreaker(name = "dollar",fallbackMethod = "fallback")
    public static Dollar getDollars() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .header("Accept","application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return getDollar(response);
    }

    private static Dollar getDollar(HttpResponse<String> response) {
        Dollar dollar = new Dollar(JsonParser
                .parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("casa")
                .getAsJsonObject()
                .get("nombre").getAsString(),
                JsonParser
                        .parseString(response.body())
                        .getAsJsonArray()
                        .get(0)
                        .getAsJsonObject()
                        .get("casa")
                        .getAsJsonObject()
                        .get("compra").getAsString(),
                JsonParser
                        .parseString(response.body())
                        .getAsJsonArray()
                        .get(0)
                        .getAsJsonObject()
                        .get("casa")
                        .getAsJsonObject()
                        .get("venta").getAsString());

        dollar.setCompra(dollar.getCompra().replace(",","."));
        dollar.setVenta(dollar.getVenta().replace(",","."));
        return dollar;
    }

    public Euro fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return Euro.builder().build();
    }


    @CircuitBreaker(name = "starPlayers",fallbackMethod = "starPlayersFallback")
    public List<StarPlayer> getStarPlayers() throws IOException, InterruptedException {

        List<StarPlayer> starPlayers = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://app.sportdataapi.com/api/v1/soccer/players?apikey=698714d0-cee6-11eb-9e8a-0db53e3af0c4&country_id=13"))
                .header("Accept","application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonObject().get("data").getAsJsonArray();

        jsonArray.forEach(o -> starPlayers.add(new Gson().fromJson(o, StarPlayer.class)));

        return starPlayers.stream().filter(p -> p.getHeight() >1.80f && p.getAge() < 20).collect(Collectors.toList());
    }

    public List<StarPlayer> starPlayersFallback(Throwable t){
        log.info("Fallback cause, {}", t.toString());
        return List.of(StarPlayer.builder().build());
    }

}
