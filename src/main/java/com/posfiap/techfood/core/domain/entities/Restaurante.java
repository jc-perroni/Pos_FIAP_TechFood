package com.posfiap.techfood.core.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
public class Restaurante {
    @Setter
    private Long id;

    private Long idProprietario;

    private String nome;

    @Setter
    private String tipoCozinha;

    private String telefone;

    @Setter
    private String endereco;

    @Setter
    private LocalTime horaAbertura;

    @Setter
    private LocalTime horaFechamento;

    public static Restaurante create(Long id, Long idProprietario, String nome,
                                     String telefone, String endereco, String tipoCozinha,
                                     LocalTime horaAbertura, LocalTime horaFechamento) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setIdProprietario(idProprietario);
        restaurante.setNome(nome);
        restaurante.setTelefone(telefone);
        restaurante.setEndereco(endereco);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setHoraAbertura(horaAbertura);
        restaurante.setHoraFechamento(horaFechamento);

        return restaurante;
    }

    //TODO: possivelmente remover
    public static Restaurante create(Long idProprietario, String nome, String telefone, String endereco) {
        Restaurante restaurante = new Restaurante();
        restaurante.setIdProprietario(idProprietario);
        restaurante.setNome(nome);
        restaurante.setTelefone(telefone);
        restaurante.setEndereco(endereco);

        return restaurante;
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
