package com.posfiap.techfood.core.domain.entities;

import com.posfiap.techfood.core.application.enums.PerfilUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class Usuario {

    @Setter
    private Long id;

    private String nome;

    @Setter
    private String email;

    private String telefone;

    @Setter
    private String cpf;

    private String username;

    private String password;

    @Setter
    private LocalDate dataCriacaoConta;

    @Setter
    private LocalDate dataAlteracaoConta;

    @Setter
    private LocalDate dataAlteracaoSenha;

    @Setter
    private PerfilUsuario perfil;

    private static void nomeValido(String nome) {
        if (nome.length() < 2) {
           throw new IllegalArgumentException("Nome não válido");
        }
    }

    private static void telefoneValido(String telefone) {
        int digitosMinimosParaTelefoneValido = 8;
        if (telefone.isBlank()) {
            return;
        }

        if (telefone.length() < digitosMinimosParaTelefoneValido) {
            throw new IllegalArgumentException("Telefone não válido");
        }
    }

    private static void usernameValido(String username) {
        if (username.length() < 3) {
            throw new IllegalArgumentException("username não válido");
        }
    }

    private static void senhaValida(String senha) {
        if (senha.isBlank()) {
            throw new IllegalArgumentException("senha não válido");
        }
    }

    public void setNome(String nome) {
        nomeValido(nome);
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        telefoneValido(telefone);
        this.telefone = telefone;
    }

    public void setUsername(String username) {
        usernameValido(username);
        this.username = username;
    }

    public void setPassword(String senha) {
        senhaValida(senha);
        this.password = senha;
    }

    public void setDataCriacaoContaAtual() {
        this.dataCriacaoConta = LocalDate.now();
    }

    public void setDataAlteracaoContaAtual() {
        this.dataAlteracaoConta = LocalDate.now();
    }

    public void setDataAlteracaoSenhaAtual() {
        this.dataAlteracaoSenha = LocalDate.now();
    }

    public static Usuario create(String nome, String email, String telefone, String cpf, String username, String password) {
        // Cria novo usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setDataCriacaoContaAtual();

        return usuario;
    }

    public static Usuario create(Long id, String nome, String email, String telefone, String cpf, String username, String password, LocalDate dataCriacaoConta, LocalDate dataAlteracaoConta, LocalDate dataAlteracaoSenha, PerfilUsuario perfil) {

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setDataCriacaoConta(dataCriacaoConta);
        usuario.setDataAlteracaoConta(dataAlteracaoConta);
        usuario.setDataAlteracaoSenha(dataAlteracaoSenha);
        usuario.setPerfil(perfil);

        return usuario;
    }
}
