package com.example.parcial.martin.caminero.model;

public enum CurrencyType {
    PESO("Peso"),
    EURO("Euro");

    private final String description;

    CurrencyType(String description) {
        this.description = description;
    }

    public static CurrencyType find(final String type){
        for(CurrencyType ct : values()){
            if(ct.toString().equalsIgnoreCase(type)){
                return ct;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid type of currency: %s",type));
    }

    public String getDescription() {
        return description;
    }
}
