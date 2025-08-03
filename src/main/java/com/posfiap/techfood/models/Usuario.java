package com.posfiap.techfood.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor

@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(length = 50)
    private String nome;

    @Getter @Setter
    @Column(length = 50)
    private String email;

    @Getter @Setter
    @Column(length = 15)
    private String telefone;

    @Column(length = 35, unique = true, nullable = false)
    @Getter @Setter
    private String username;

    @Getter
    @Column(length = 60)
    private String password;

    @Getter @Setter
    @Column(length = 14)
    private String cpf;

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
