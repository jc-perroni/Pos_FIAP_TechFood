package com.posfiap.techfood.core.application.dto;

import lombok.Getter;

@Getter
public abstract class AlteracaoSenhaDTO {
    private String usuario;
    private String senhaNova;
    private String senhaAntiga;
}
