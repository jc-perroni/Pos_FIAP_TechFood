package com.posfiap.techfood.models.dto.proprietario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProprietarioDTO(String telefone,

                              @NotNull(message = "O campo 'nome' não pode ser nulo")
                              String nome,

                              @NotNull(message = "O campo 'CPF' não pode ser nulo")
                              String cpf,

                              @NotNull(message = "O campo 'email' não pode ser nulo")
                              String email,

                              @NotNull(message = "O campo 'usernam' não pode ser nulo")
                              String username,

                              @NotNull(message = "O campo 'password' não pode ser nulo")
                              String password,

                              LocalDate dataCriacaoConta,
                              LocalDate dataAlteracaoConta,
                              LocalDate dataAlteracaoSenha) {
}
