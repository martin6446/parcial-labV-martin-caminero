package com.example.parcial.martin.caminero.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;



@JsonTypeInfo(use = JsonTypeInfo.Id.NAME ,property = "personType",visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Player.class ,name = "PLAYER"),
    @JsonSubTypes.Type(value = Manager.class ,name = "MANAGER")
})

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String lastName;

    @AccessType(value = AccessType.Type.PROPERTY)
    public abstract PersonType personType();

}
