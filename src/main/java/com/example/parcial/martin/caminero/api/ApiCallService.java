package com.example.parcial.martin.caminero.api;

import com.example.parcial.martin.caminero.model.Dollar;
import com.example.parcial.martin.caminero.model.Euro;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

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
                .header("Accept","application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();



        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return getEuro(response);
        //return new Gson().fromJson(precioEuro, Euro.class);
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

        JsonObject precioDolar = JsonParser.parseString(response.body()).getAsJsonArray().get(0).getAsJsonObject().get("casa").getAsJsonObject();


        return getDollar(response);
        //return new Gson().fromJson(precioDolar, Dollar.class);
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

}
