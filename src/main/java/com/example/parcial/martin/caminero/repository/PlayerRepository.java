package com.example.parcial.martin.caminero.repository;

import com.example.parcial.martin.caminero.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
