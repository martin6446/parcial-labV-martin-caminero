package com.example.parcial.martin.caminero.model.dto;

import com.example.parcial.martin.caminero.model.Person;
import com.example.parcial.martin.caminero.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

import static com.example.parcial.martin.caminero.utils.UtilClass.converter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {

    private String name;

    private String currency;

    private int amount;


    public static PlayerDto from(Player player) throws IOException, InterruptedException {
        return PlayerDto.builder()
                .name(player.getName())
                .currency(player.getCurrency().getCurrency().getDescription())
                .amount(converter(player))
                .build();
    }
}
