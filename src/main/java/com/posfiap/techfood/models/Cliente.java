package com.posfiap.techfood.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    @Getter @Setter
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(
            String nome,
            String email,
            String telefone,
            String cpf,
            String username,
            LocalDate dataCriacaoConta,
            LocalDate dataAlteracaoConta,
            LocalDate dataAlteracaoSenha
    ) {
        super(nome, email, telefone, cpf, username,dataCriacaoConta, dataAlteracaoConta,dataAlteracaoSenha);
    }

}