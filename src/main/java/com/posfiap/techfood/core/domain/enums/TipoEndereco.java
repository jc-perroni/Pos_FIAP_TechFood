package com.posfiap.techfood.core.domain.enums;

public enum TipoEndereco {

    CLIENTE(1),
    RESTAURANTE(2);

    private final int codigo;

    TipoEndereco(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo(){
        return codigo;
    }
}
