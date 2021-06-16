package com.example.parcial.martin.caminero.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {

    @Id
    @SerializedName("country_id")
    private int countryId;

    private String name;

    @SerializedName("country_code")
    private String countryCode;

    private String continent;
}
