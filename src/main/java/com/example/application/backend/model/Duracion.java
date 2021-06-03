package com.example.application.backend.model;

public enum Duracion {
    MESES("M",6),
    ANYO("A",1),
    DOSANYOS("A",2);
    private final String value;
    private final int periodo;

    Duracion(String value, int periodo) {
        this.value = value;
        this.periodo = periodo;
    }

    public String getValue() {
        return value;
    }

    public int getPeriodo() {
        return periodo;
    }
}

