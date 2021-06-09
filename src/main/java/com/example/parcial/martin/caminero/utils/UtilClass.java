package com.example.parcial.martin.caminero.utils;

import com.example.parcial.martin.caminero.model.Currency;
import com.example.parcial.martin.caminero.model.Dollar;
import com.example.parcial.martin.caminero.model.Euro;
import com.example.parcial.martin.caminero.model.Player;

import java.io.IOException;

import static com.example.parcial.martin.caminero.api.ApiCallService.getDollars;
import static com.example.parcial.martin.caminero.api.ApiCallService.getEuros;

public class UtilClass {

    public static int converter(Player player) throws IOException, InterruptedException {
        Euro euro = getEuros();
        Dollar dollar = getDollars();

        if(player.getCurrency().getCurrency().getDescription().equalsIgnoreCase("euro")){
            return player.getCurrency().getTotal() * Integer.parseInt(euro.getCompra());
        } else if(player.getCurrency().getCurrency().getDescription().equalsIgnoreCase("dollar"))
        {
            return player.getCurrency().getTotal() * Integer.parseInt(dollar.getCompra());
        } else
        {
            return player.getCurrency().getTotal();
        }
    }
}
