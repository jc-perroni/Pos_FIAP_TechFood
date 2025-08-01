package com.posfiap.techfood.core.domain.entities;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Restaurante {
    private Long id;
    private Long idProprietario;
    private String nome;
    private String telefone;
    private String endereco;

    public static Restaurante create(Long id, Long idProprietario, String nome, String telefone, String endereco) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setIdProprietario(idProprietario);
        restaurante.setNome(nome);
        restaurante.setTelefone(telefone);
        restaurante.setEndereco(endereco);

        return restaurante;
    }

    public static Restaurante create(Long idProprietario, String nome, String telefone, String endereco) {
        Restaurante restaurante = new Restaurante();
        restaurante.setIdProprietario(idProprietario);
        restaurante.setNome(nome);
        restaurante.setTelefone(telefone);
        restaurante.setEndereco(endereco);

        return restaurante;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdProprietario(Long idProprietario) {
        idProprietarioValido(idProprietario);
        this.idProprietario = idProprietario;
    }

    public void setNome(String nome) {
        nomeValido(nome);
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        telefoneValido(telefone);
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    private static void nomeValido(String nome) {
        if (Objects.isNull(nome) || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    private static void idProprietarioValido(Long idProprietario) {
        if (Objects.isNull(idProprietario)) {
            throw new IllegalArgumentException("Proprietário não pode ser vazio");
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
}
