package com.posfiap.techfood.infra.models.enums;

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
