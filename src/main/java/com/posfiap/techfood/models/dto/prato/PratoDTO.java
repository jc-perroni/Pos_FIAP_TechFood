package com.posfiap.techfood.models.dto.prato;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PratoDTO(
        @NotNull(message = "O nome do prato não pode ser nulo")
        String nome,

        String descricao,

        @NotNull(message = "O preço não pode ser nulo")
        @Positive(message = "O preço deve ser positivo")
        Double preco,

        @NotNull(message = "A disponibilidade para consumo local não pode ser nula")
        Boolean apenasConsumoLocal,

        String linkImagem
) {}