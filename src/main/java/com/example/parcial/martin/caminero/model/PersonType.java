package com.example.parcial.martin.caminero.model;

public enum PersonType {
    PLAYER("Player"),
    MANAGER("Manager");

    private String description;

    PersonType(String description) {
        this.description = description;
    }

    public static PersonType find(String type){
        for(PersonType pt : values()){
            if(pt.toString().equalsIgnoreCase(type)){
                return pt;
            }
        }
        throw new IllegalArgumentException(String.format("type of person mismatch: %s",type));
    }

    public String getDescription() {
        return description;
    }
}
