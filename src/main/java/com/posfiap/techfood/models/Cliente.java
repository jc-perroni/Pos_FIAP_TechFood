package com.posfiap.techfood.models;

import java.time.LocalDate;

public class Cliente extends Usuario {

    public Cliente(String telefone,
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

}