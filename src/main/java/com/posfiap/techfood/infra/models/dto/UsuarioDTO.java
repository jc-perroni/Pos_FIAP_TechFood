package com.posfiap.techfood.infra.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de retorno do login, contendo informações básicas do usuário")
public record UsuarioDTO(

    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    String nome,

    @Schema(description = "ID único do usuário", example = "123")
    Long id

) {}
