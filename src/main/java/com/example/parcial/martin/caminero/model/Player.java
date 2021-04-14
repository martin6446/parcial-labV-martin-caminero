package com.example.parcial.martin.caminero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Player extends Person{

    private float weight;
    private float height;
    private int goals;
    private int minutesPlayed;
    private LocalDate birthday;

    @Override
    public PersonType personType() {
        return PersonType.PLAYER;
    }

    @OneToOne(fetch = FetchType.EAGER)
    private Currency currency;

}
