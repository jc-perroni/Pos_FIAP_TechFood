package com.posfiap.techfood.models;

import java.time.LocalDate;
import java.util.List;

public class Proprietario extends Usuario{

    private List<Restaurante> managerBusiness;

    public Proprietario(String telefone,
                        String nome,
                        String CPF,
                        String email,
                        String username,
                        String password,
                        LocalDate dataCriacaoConta,
                        LocalDate dataAlteracaoConta,
                        LocalDate dataAlteracaoSenha) {
        super(nome, CPF, telefone, email, username, password, dataCriacaoConta, dataAlteracaoConta, dataAlteracaoSenha);
    }
}
