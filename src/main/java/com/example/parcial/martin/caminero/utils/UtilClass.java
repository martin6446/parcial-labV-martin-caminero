package com.example.parcial.martin.caminero.utils;

import com.example.parcial.martin.caminero.model.Dollar;
import com.example.parcial.martin.caminero.model.Euro;
import com.example.parcial.martin.caminero.model.Player;

import java.io.IOException;

import static com.example.parcial.martin.caminero.api.ApiCallService.getDollars;
import static com.example.parcial.martin.caminero.api.ApiCallService.getEuros;

public class UtilClass {

    public static float converter(Player player) throws IOException, InterruptedException {
        Euro euro = getEuros();
        Dollar dollar = getDollars();

        if(player.getCurrency().getCurrency().getDescription().equalsIgnoreCase("euro")){
            return 25000 / Float.parseFloat(euro.getCompra());
        } else if(player.getCurrency().getCurrency().getDescription().equalsIgnoreCase("dollar"))
        {
            return 25000 / Float.parseFloat(dollar.getCompra());
        } else
        {
            return player.getCurrency().getTotal();
        }
    }
}
