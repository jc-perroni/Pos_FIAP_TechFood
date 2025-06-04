package com.posfiap.techfood.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteUpdateDTO(@NotNull(message = "O campo 'nome' não pode ser nulo")
                               String nome,

                               String cpf,

                               @NotNull(message = "O campo 'username' não pode ser nulo")
                               String telefone,

                               @Email
                               @NotNull(message = "O campo 'email' não pode ser nulo")
                               String email) {
}
