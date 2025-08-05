package com.posfiap.techfood.core.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class ItemCardapio {

    @Setter
    private Long id;

    private Long idRestaurante;

    private String nome;

    @Setter
    private String descricao;

    private BigDecimal valor;

    private Boolean apenasPedidosPresenciais;

    @Setter
    private String foto;

    public static ItemCardapio create(Long id, Long idRestaurante, String nome, String descricao,
                                      BigDecimal valor, Boolean apenasPedidosPresenciais, String foto) {
        ItemCardapio itemCardapio = new ItemCardapio();
        itemCardapio.setId(id);
        itemCardapio.setIdRestaurante(idRestaurante);
        itemCardapio.setNome(nome);
        itemCardapio.setDescricao(descricao);
        itemCardapio.setValor(valor);
        itemCardapio.setApenasPedidosPresenciais(apenasPedidosPresenciais);
        itemCardapio.setFoto(foto);

        return itemCardapio;
    }

    public void setIdRestaurante(Long idRestaurante) {
        idRestauranteValido(idRestaurante);
        this.idRestaurante = idRestaurante;
    }

    private static void idRestauranteValido(Long idRestaurante) {
        if (Objects.isNull(idRestaurante)) {
            throw new IllegalArgumentException("Restaurante não pode ser vazio");
        }
    }

    public void setNome(String nome) {
        nomeValido(nome);
        this.nome = nome;
    }

    private static void nomeValido(String nome) {
        if (Objects.isNull(nome) || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    public void setValor(BigDecimal valor) {
        valorValido(valor);
        this.valor = valor;
    }

    private static void valorValido(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser menor que zero");
        }
    }

    public void setApenasPedidosPresenciais(Boolean apenasPedidosPresenciais) {
        apenasPedidosPresenciaisValido(apenasPedidosPresenciais);
        this.apenasPedidosPresenciais = apenasPedidosPresenciais;
    }

    private static void apenasPedidosPresenciaisValido(Boolean apenasPedidosPresenciais) {
        if (Objects.isNull(apenasPedidosPresenciais)) {
            throw new IllegalArgumentException("Disponibilidade para pedir apenas no restaurante não pode ser vazio");
        }
    }
}
