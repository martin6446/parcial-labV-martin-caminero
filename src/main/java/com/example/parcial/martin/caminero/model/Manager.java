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
    private int totalAmount;


    @Override
    public PersonType personType() {
        return PersonType.MANAGER;
    }


    public int getTotalAmount(){

        int value = 0;

        for(Player p : players){
            if(p.getCurrency().getCurrency().equals(CurrencyType.DOLLAR)){
                value += p.getCurrency().getTotal() * 160;
            }else if(p.getCurrency().getCurrency().equals(CurrencyType.EURO)){
                value += p.getCurrency().getTotal() * 340;
            }else {
                value += p.getCurrency().getTotal();
            }
        }

        return value;
        // esto creo que estaba mal dejarlo asi como estaba
        //return players.stream().map(player -> player.getCurrency().getTotal()).reduce(0,Integer::sum);
    }

    // no se si esta bien pero recuendo que German hablaba algo de que todos los billetes son de 100 y bueno esto es lo que se me ocurrio
    public float getVaultWeight() {
        return (float)(getTotalAmount() / 100);
    }
}
