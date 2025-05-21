package com.posfiap.techfood.models;

import java.util.List;

public class Proprietario extends Usuario{

    private List<Restaurante> managerBusiness;

    public Proprietario(String telefone, String nome, String CPF, String email, String login, String password) {
        super(telefone, email, nome, CPF, login, password);
    }
}
