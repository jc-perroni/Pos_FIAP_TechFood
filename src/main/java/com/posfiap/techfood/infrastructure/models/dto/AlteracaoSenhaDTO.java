package com.posfiap.techfood.infrastructure.models.dto;

import lombok.Getter;

@Getter
public abstract class AlteracaoSenhaDTO {
    private String usuario;
    private String senhaNova;
    private String senhaAntiga;
}
