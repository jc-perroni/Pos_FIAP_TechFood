package com.posfiap.techfood.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "RESTAURANTES")
public class Restaurante {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "ID_PROPRIETARIO")
    private Proprietario proprietario;

    @Getter @Setter
    @Column(length = 100)
    private String nome;

    @Getter @Setter
    @Column(length = 100)
    private String telefone;

    @Getter @Setter
    @JsonManagedReference("restaurante-endereco")
    @OneToMany(mappedBy = "restaurante")
    private List<Endereco> enderecos;
}
