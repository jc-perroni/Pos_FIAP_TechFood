package com.posfiap.techfood.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.posfiap.techfood.models.dto.endereco.EnderecoDTO;
import com.posfiap.techfood.models.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ENDERECOS")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    @JsonBackReference("cliente-endereco")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "ID_CLIENTE")
    private Usuario usuario;

    @ManyToOne
    @Setter
    @JsonBackReference("restaurante-endereco")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "ID_RESTAURANTE")
    private Restaurante restaurante;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column(name = "TIPO_ENDERECO", nullable = false)
    private TipoEndereco tipoEndereco;

    @Setter
    @Column(nullable = false, length = 50)
    private String rua;

    @Setter
    @Column(nullable = false, length = 15)
    private String cep;

    @Setter
    @Column(nullable = false, length = 15)
    private String cidade;

    @Setter
    @Column(nullable = false, length = 20)
    private String bairro;

    @Setter
    @Column(length = 30)
    private String complemento;

    @Setter
    @Column(nullable = false, length = 10)
    private String numero;

    public static Endereco fromDTO(EnderecoDTO dto, Usuario cliente) {
        Endereco endereco = new Endereco();
        endereco.setUsuario(cliente);
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setBairro(dto.bairro());
        endereco.setComplemento(dto.complemento());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setTipoEndereco(dto.tipoEndereco());
        return endereco;
    }

    public static Endereco fromDTO(EnderecoDTO dto, Restaurante restaurante) {
        Endereco endereco = new Endereco();
        endereco.setRestaurante(restaurante);
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setBairro(dto.bairro());
        endereco.setComplemento(dto.complemento());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setTipoEndereco(dto.tipoEndereco());
        return endereco;
    }
}