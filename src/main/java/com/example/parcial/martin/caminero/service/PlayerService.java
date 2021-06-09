package com.example.parcial.martin.caminero.service;

import com.example.parcial.martin.caminero.exception.PersonNotFoundException;
import com.example.parcial.martin.caminero.model.Player;
import com.example.parcial.martin.caminero.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void add(Player player){
        playerRepository.save(player);
    }

    public Player getById(int id){
        return playerRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    public void delete(int id){
        playerRepository.deleteById(id);
    }


}

