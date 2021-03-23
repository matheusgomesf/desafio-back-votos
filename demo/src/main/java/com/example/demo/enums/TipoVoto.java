package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum TipoVoto {

    SIM("SIM"),
    NAO("NAO");

    private final String tipoVoto;

    TipoVoto(String tipoVoto) {
        this.tipoVoto = tipoVoto;
    }
}
