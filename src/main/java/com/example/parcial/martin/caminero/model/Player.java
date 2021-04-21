package com.example.parcial.martin.caminero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Player extends Person{

    private float weight;
    private float height;
    private int goals;
    private int minutesPlayed;
    private LocalDate birthday;

    @OneToOne(fetch = FetchType.EAGER)
    private Currency currency;

    @Override
    public PersonType personType() {
        return PersonType.PLAYER;
    }



}
