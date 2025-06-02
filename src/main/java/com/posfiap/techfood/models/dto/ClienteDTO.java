package com.posfiap.techfood.models.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(
        String telefone,
        String CPF,

        @NotNull(message = "O campo 'nome' não pode ser nulo")
        String nome,

        @NotNull(message = "O campo 'email' não pode ser nulo")
        String email,

        @NotNull(message = "O campo 'username' não pode ser nulo")
        String username,

        @NotNull(message = "O campo 'password' não pode ser nulo")
        String password,

        LocalDate dataCriacaoConta,
        LocalDate dataAlteracaoConta,
        LocalDate dataAlteracaoSenha
) {
}
