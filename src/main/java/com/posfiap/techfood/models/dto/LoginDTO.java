package com.posfiap.techfood.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO base para login, contendo e-mail e senha")
public class LoginDTO {

    @Schema(description = "E-mail do usuário", example = "usuario@email.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    private String senha;
}
