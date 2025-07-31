package com.posfiap.techfood.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class Usuario {
    private int id;
    private String tipoUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String username;
    private String password;
    private LocalDate dataCriacaoConta;
    private LocalDate dataAlteracaoConta;
    private LocalDate dataAlteracaoSenha;

    private static void nomeValido(String nome) {
        if (nome.length() < 2) {
           throw new IllegalArgumentException("Nome não válido");
        }
    }

    private static void enderecoEmailValido(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)) {
            throw new IllegalArgumentException("Endereço de email não válido");
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

    private static void cpfValido(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF não válido");
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

    public void setEmail(String email) {
        enderecoEmailValido(email);
        this.email = email;
    }

    public void setTelefone(String telefone) {
        telefoneValido(telefone);
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        cpfValido(cpf);
        this.cpf = cpf;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setDataCriacaoConta(LocalDate dataCriacaoConta) {
        this.dataCriacaoConta = dataCriacaoConta;
    }

    public void setDataAlteracaoConta(LocalDate dataAlteracaoConta) {
        this.dataAlteracaoConta = dataAlteracaoConta;
    }

    public void setDataAlteracaoSenha(LocalDate dataAlteracaoSenha) {
        this.dataAlteracaoSenha = dataAlteracaoSenha;
    }

    public static Usuario create(String tipoUsuario, String nome, String email, String telefone, String cpf, String username, String password) {
        // Cria novo usuario
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setDataCriacaoContaAtual();

        return usuario;
    }

    public static Usuario create(int id, String tipoUsuario, String nome, String email, String telefone, String cpf, String username, String password, LocalDate dataCriacaoConta, LocalDate dataAlteracaoConta, LocalDate dataAlteracaoSenha) {

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setCpf(cpf);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setDataCriacaoConta(dataCriacaoConta);
        usuario.setDataAlteracaoConta(dataAlteracaoConta);
        usuario.setDataAlteracaoSenha(dataAlteracaoSenha);

        return usuario;
    }
}
