package com.example.parcial.martin.caminero.controller;

import com.example.parcial.martin.caminero.api.ApiCallService;
import com.example.parcial.martin.caminero.model.Player;
import com.example.parcial.martin.caminero.model.dto.PlayerDto;
import com.example.parcial.martin.caminero.model.dto.StarPlayer;
import com.example.parcial.martin.caminero.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final ApiCallService apiCallService;

    @Autowired
    public PlayerController(PlayerService playerService, ApiCallService apiCallService) {
        this.playerService = playerService;
        this.apiCallService = apiCallService;
    }

    @GetMapping("/fiesta/deudores")
    public List<PlayerDto> getDebtors() throws IOException, InterruptedException {
        List<Player> players = playerService.getAll();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player p : players){
            playerDtos.add(PlayerDto.from(p));
        }

        return playerDtos;
    }

    @GetMapping("/fiesta/starPlayers")
    public List<StarPlayer> getStarPlayers() throws IOException, InterruptedException {
        return apiCallService.getStarPlayers();
    }
}
