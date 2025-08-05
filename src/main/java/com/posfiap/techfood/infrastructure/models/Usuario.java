package com.posfiap.techfood.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "USUARIOS")
public class Usuario {

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter @Setter
    private PerfilUsuario perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    @JsonManagedReference("cliente-endereco")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurante> restaurantes;

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
