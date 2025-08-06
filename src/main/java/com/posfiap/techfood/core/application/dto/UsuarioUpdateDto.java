package com.posfiap.techfood.core.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDto(@NotNull(message = "O campo 'nome' não pode ser nulo")
                               String nome,

                               @NotNull(message = "O campo 'cpf' não pode ser nulo")
                               String cpf,

                               String telefone,

                               @Email
                               @NotNull(message = "O campo 'email' não pode ser nulo")
                               String email) {
}
