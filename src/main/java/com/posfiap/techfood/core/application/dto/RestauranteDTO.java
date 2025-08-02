package com.posfiap.techfood.core.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestauranteDTO (
        Long id,

        @NotNull(message = "O ID do proprietário não pode ser nulo")
        Long idProprietario,

        @NotBlank(message = "O nome do restaurante não pode ser vazio")
        String nome,

        String tipoCozinha,

        String telefone,

        String endereco,

        LocalTime horaAbertura,

        LocalTime horaFechamento
) {
}
