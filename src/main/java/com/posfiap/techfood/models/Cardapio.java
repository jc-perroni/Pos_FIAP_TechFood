package com.posfiap.techfood.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "CARDAPIOS")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_RESTAURANTE", unique = true)
    @JsonBackReference
    private Restaurante restaurante;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cardapio-prato")
    private List<Prato> pratos;


}
