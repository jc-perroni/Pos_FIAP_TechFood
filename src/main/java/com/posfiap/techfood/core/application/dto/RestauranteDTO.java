package com.posfiap.techfood.core.application.dto;

import java.time.LocalTime;

public record RestauranteDTO (
        Long id,
        Long idProprietario,
        String nome,
        String tipoCozinha,
        String telefone,
        String endereco,
        LocalTime horaAbertura,
        LocalTime horaFechamento
) {
}
