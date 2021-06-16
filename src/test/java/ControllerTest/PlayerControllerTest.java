package ControllerTest;

import com.example.parcial.martin.caminero.api.ApiCallService;
import com.example.parcial.martin.caminero.controller.PlayerController;
import com.example.parcial.martin.caminero.model.dto.PlayerDto;
import com.example.parcial.martin.caminero.model.dto.StarPlayer;
import com.example.parcial.martin.caminero.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static UtilsTest.UtilsTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {

    PlayerService playerService;
    PlayerController playerController;
    ApiCallService apiCallService;

    @BeforeEach
    public void setUp(){
        playerService = mock(PlayerService.class);
        apiCallService = mock(ApiCallService.class);
        playerController = new PlayerController(playerService, apiCallService);
    }


    @Test
    public void getDebtorsTestOk() throws IOException, InterruptedException {
        when(playerService.getAll()).thenReturn(List.of(aPlayer()));

        List<PlayerDto> players  = playerController.getDebtors();

        assertNotNull(players);
        assertEquals(players.get(0).getName(),aPlayerDto().getName());
    }

    @Test
    public void getStarPlayersTestOk() throws IOException, InterruptedException {
        when(apiCallService.getStarPlayers()).thenReturn(List.of(aStarPlayer()));

        List<StarPlayer> players = playerController.getStarPlayers();

        assertNotNull(players);
        assertEquals(players.get(0).getFirstName(),aStarPlayer().getFirstName());
    }
}
