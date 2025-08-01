package com.posfiap.techfood.core.application.dto;

public record RestauranteDTO (
        Long id,
        Long idProprietario,
        String nome,
        String telefone,
        String endereco
) {
}
