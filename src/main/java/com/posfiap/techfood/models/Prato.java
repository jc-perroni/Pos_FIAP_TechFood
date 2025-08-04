package com.posfiap.techfood.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "PRATOS")
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String nome;
    @Setter
    private String descricao;
    @Setter
    private double preco;
    @Setter
    private Boolean apenasConsumoLocal;
    @Setter
    private String linkImagem;

    @ManyToOne
    @JoinColumn(name = "ID_CARDAPIO")
    @JsonBackReference("cardapio-prato")
    @Setter
    private Cardapio cardapio;

}
