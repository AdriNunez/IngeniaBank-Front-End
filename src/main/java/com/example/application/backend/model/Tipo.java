package com.example.application.backend.model;

public enum Tipo {
    FIJO("Fijo",0.05);

    private final String value;
    private final Double interes;

    Tipo(String value, Double interes) {
        this.value = value;
        this.interes = interes;
    }

    public String getValue() {
        return value;
    }

    public Double getInteres() {
        return interes;
    }
}
