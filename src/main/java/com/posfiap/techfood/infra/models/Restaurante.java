package com.posfiap.techfood.infra.models;

import lombok.Getter;
import lombok.Setter;

public class Restaurante {

    @Getter
    private Long id;
    @Getter
    private Long idProprietario;
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private String telefone;
    @Getter @Setter
    private Endereco endereco;
}
