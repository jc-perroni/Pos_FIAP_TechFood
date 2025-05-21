package com.posfiap.techfood.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public abstract class Usuario {

    public Usuario(String nome, String email, String telefone, String CPF, String login, String password) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.CPF = CPF;
        this.login = login;
        this.password = password;
    }
    @Getter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String telefone;

    @Getter @Setter
    private String CPF;

    @Getter
    private final String login;

    @Getter
    private String password;

    @Getter
    private LocalDate dataCriacaoConta;

    @Getter
    private LocalDate dataUltimaAlteracaoConta;

    @Getter
    private LocalDate dataUltimaAlteracaoSenha;

    public void criarConta(String password){
        this.password = password;
        this.dataCriacaoConta = LocalDate.now();

    }
    public void alterarSenha(String password){
        this.password = password;
        this.dataUltimaAlteracaoSenha = LocalDate.now();
    }




}
