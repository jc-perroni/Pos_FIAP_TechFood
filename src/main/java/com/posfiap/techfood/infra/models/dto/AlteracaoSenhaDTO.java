package com.posfiap.techfood.infra.models.dto;

import lombok.Getter;

@Getter
public abstract class AlteracaoSenhaDTO {
    private String usuario;
    private String senhaNova;
    private String senhaAntiga;
}
