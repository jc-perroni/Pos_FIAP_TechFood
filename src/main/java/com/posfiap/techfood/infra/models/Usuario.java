package com.posfiap.techfood.infra.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public abstract class Usuario {

    public Usuario() {
    }

    public Usuario(String nome, String email, String telefone, String cpf, String username, String password,
                   LocalDate dataCriacaoConta, LocalDate dataAlteracaoConta, LocalDate dataAlteracaoSenha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.username = username;
        this.password = password;
        this.dataCriacaoConta = dataCriacaoConta;
        this.dataAlteracaoConta = dataAlteracaoConta;
        this.dataAlteracaoSenha = dataAlteracaoSenha;
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
    private String cpf;

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    private LocalDate dataCriacaoConta;

    @Getter
    private LocalDate dataAlteracaoConta;

    @Getter
    private LocalDate dataAlteracaoSenha;

    public void criarConta(String password){
        this.password = password;
        this.dataCriacaoConta = LocalDate.now();

    }
    public void alterarSenha(String password){
        this.password = password;
        this.dataAlteracaoSenha = LocalDate.now();
    }

    public void updateDataAlteracao() {
        this.dataAlteracaoConta = LocalDate.now();
    }

}
