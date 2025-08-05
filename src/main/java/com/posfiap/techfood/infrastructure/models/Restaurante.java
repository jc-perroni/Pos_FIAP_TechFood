package com.posfiap.techfood.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.posfiap.techfood.infrastructure.models.enums.TipoCozinha;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "RESTAURANTES")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ID_PROPRIETARIO")
    private Usuario usuario;

    @Setter
    @Column(length = 100)
    private String nome;

    @Setter
    @Column(length = 100)
    private String telefone;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "GASTRONOMIA", nullable = false)
    private TipoCozinha tipoCozinha;

    @Setter
    @Column(length = 100, nullable = false)
    private String horarioFuncionamento;

    @Setter
    @JsonManagedReference("restaurante-endereco")
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @Setter
    @OneToOne(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Cardapio cardapio;
}
