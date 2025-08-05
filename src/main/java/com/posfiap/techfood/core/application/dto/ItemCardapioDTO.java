package com.posfiap.techfood.core.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemCardapioDTO(
        Long id,

        @NotNull(message = "O ID do restaurante não pode ser nulo")
        Long idRestaurante,

        @NotBlank(message = "O nome do item não pode ser vazio")
        String nome,

        String descricao,

        @NotNull(message = "O valor do item não pode ser nulo")
        BigDecimal valor,

        @NotNull(message = "Disponibilidade para pedir apenas no restaurante não pode ser nula")
        Boolean apenasPedidosPresenciais,

        String foto
) {
}
