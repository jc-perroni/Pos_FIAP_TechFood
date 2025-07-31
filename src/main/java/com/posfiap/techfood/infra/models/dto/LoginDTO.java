package com.posfiap.techfood.infra.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO base para login, contendo e-mail e senha")
public class LoginDTO {

    @Schema(description = "Username para login.", example = "usuario.cliente")
    private String usuario;

    @Schema(description = "Senha do usuário", example = "senha123")
    private String senha;
}
