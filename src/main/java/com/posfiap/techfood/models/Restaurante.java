package com.posfiap.techfood.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Restaurante {

    @Getter
    private Long id;
    @Getter
    private Long idProprietario;
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private String telefone;
    @Getter
    private Endereco endereco;


}
