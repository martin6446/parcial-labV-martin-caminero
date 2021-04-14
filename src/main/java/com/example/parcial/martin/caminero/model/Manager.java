package com.example.parcial.martin.caminero.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Manager extends Person{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Manager_id")
    private List<Player> players;

    private float vaultWeight;
    private float totalAmount;


    @Override
    public PersonType personType() {
        return PersonType.MANAGER;
    }


}
