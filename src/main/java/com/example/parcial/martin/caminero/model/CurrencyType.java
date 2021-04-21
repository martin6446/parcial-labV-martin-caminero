package com.example.parcial.martin.caminero.model;

public enum CurrencyType {
    EURO("Euro"),
    PESO("Peso"),
    DOLLAR("Dollar");

    private final String description;

    CurrencyType(String description) {
        this.description = description;
    }

    public static CurrencyType get(String curType){
        for (CurrencyType ct : values()){
            if(ct.toString().equalsIgnoreCase(curType)){
                return ct;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid Currency Type: %s",curType));
    }

    public String getDescription() {
        return description;
    }
}
