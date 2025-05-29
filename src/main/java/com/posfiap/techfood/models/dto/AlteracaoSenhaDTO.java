package com.posfiap.techfood.models.dto;

import lombok.Getter;

@Getter
public abstract class AlteracaoSenhaDTO {
    private String usuario;
    private String senhaNova;
    private String senhaAntiga;
}
