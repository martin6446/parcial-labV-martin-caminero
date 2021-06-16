package com.example.parcial.martin.caminero.apiTest;

import com.example.parcial.martin.caminero.api.ApiCallService;
import com.example.parcial.martin.caminero.model.dto.StarPlayer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiCallServiceTest {

    ApiCallService apiCallService = new ApiCallService();


    @Test
    public void getStarPlayersTestOk() throws IOException, InterruptedException {


        //no se me ocurre una manera real de testear este metodo
        // lo unico que se me ocurrio es hacer una llamada real y verificar que esto traiga datos
        // no se como testear nada de lo que esta dentro de getStarPlayers


        List<StarPlayer> players = apiCallService.getStarPlayers();

        assertNotNull(players);
        assertEquals(players.get(0).getClass(),StarPlayer.class);
    }
}
