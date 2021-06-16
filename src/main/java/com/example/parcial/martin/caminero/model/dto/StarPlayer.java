package com.example.parcial.martin.caminero.model.dto;

import com.example.parcial.martin.caminero.model.Country;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StarPlayer   {

    @Id
    @SerializedName("player_id")
    private int id;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    private int age;

    private float height;

    @OneToOne
    private Country country;

}
