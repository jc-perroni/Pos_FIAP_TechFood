package com.posfiap.techfood.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {

    CLIENTE(1),
    PROPRIETÁRIO(2);

    private final int id;

    TipoUsuario(int id) {
        this.id = id;
    }

    @JsonValue
    public int getId() {
        return id;
    }

    @JsonCreator
    public static TipoUsuario fromId(int id) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoUsuario inválido: " + id);
    }
}