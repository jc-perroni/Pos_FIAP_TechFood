package com.posfiap.techfood.models.dto.usuario;

import lombok.Getter;

@Getter
public abstract class AlteracaoSenhaDTO {
    private String username;
    private String senhaNova;
    private String senhaAntiga;
}
