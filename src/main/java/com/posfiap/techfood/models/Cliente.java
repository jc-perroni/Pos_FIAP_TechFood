package com.posfiap.techfood.models;

public class Cliente extends Usuario {

    public Cliente(String telefone,
                   String CPF,
                   String nome,
                   String email,
                   String login,
                   String password
                   ) {
        super(nome, email, telefone, CPF, login, password);
    }

}