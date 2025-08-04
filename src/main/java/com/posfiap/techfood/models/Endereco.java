package com.posfiap.techfood.models;

import com.posfiap.techfood.models.dto.EnderecoDTO;
import com.posfiap.techfood.core.domain.enums.TipoEndereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Endereco {
    Long id;

    Long idEntidade;

    TipoEndereco tipoEndereco;

    @Setter
    String rua;

    @Setter
    String cep;

    @Setter
    String cidade;

    @Setter
    String bairro;

    @Setter
    String complemento;

    @Setter
    String numero;

    public Endereco(Long id, Long idEntidade, TipoEndereco tipoEndereco, String rua, String cep, String cidade, String bairro, String complemento, String numero) {
        this.id = id;
        this.idEntidade = idEntidade;
        this.tipoEndereco = tipoEndereco;
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
    }

    public Endereco(EnderecoDTO enderecoDTO) {
        this.idEntidade = enderecoDTO.idEntidade();
        this.tipoEndereco = enderecoDTO.tipoEndereco();
        this.rua = enderecoDTO.rua();
        this.cep = enderecoDTO.cep();
        this.cidade = enderecoDTO.cidade();
        this.bairro = enderecoDTO.bairro();
        this.complemento = enderecoDTO.complemento();
        this.numero = enderecoDTO.numero();
    }
}