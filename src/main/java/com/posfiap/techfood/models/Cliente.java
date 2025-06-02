package com.posfiap.techfood.models;

import com.posfiap.techfood.models.dto.ClienteDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    @Getter @Setter
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(
            String telefone,
            String CPF,
            String nome,
            String email,
            String username,
            String password,
            LocalDate dataCriacaoConta,
            LocalDate dataAlteracaoConta,
            LocalDate dataAlteracaoSenha
    ) {
        super(nome, email, telefone, CPF, username, password,dataCriacaoConta, dataAlteracaoConta,dataAlteracaoSenha);
    }

    public Cliente(ClienteDTO cliente) {
        super(cliente.nome(), cliente.email(), cliente.telefone(),
                cliente.CPF(), cliente.username(), cliente.password(),
                cliente.dataCriacaoConta(), cliente.dataAlteracaoConta(),
                cliente.dataAlteracaoSenha());
    }

}