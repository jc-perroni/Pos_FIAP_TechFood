package com.posfiap.techfood.core.domain.entities;

import com.posfiap.techfood.core.domain.enums.TipoEndereco;
import com.posfiap.techfood.core.domain.exceptions.CepNaoValidoException;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class Endereco {
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    @NotNull
    private Long idEntidade;

    @Getter
    @Setter
    @NotNull
    private TipoEndereco tipoEndereco;

    @Getter
    @NotNull
    @Setter
    private String rua;

    @Getter
    @NotNull
    private String cep;

    @Getter
    @NotNull
    @Setter
    private String cidade;

    @Getter
    @NotNull
    @Setter
    private String bairro;

    @Getter
    @Setter
    private String complemento;

    @Getter
    @NotNull
    @Setter
    private String numero;

    private static void cepValido(String cep) {
        if (cep.length() != 8) {
            throw new CepNaoValidoException("Cep: " + cep + " não contem o numero mínimo de dígitos necessários");
        }
    }

    public void setCep(String cep) {
        cepValido(cep);
        this.cep = cep;
    }

    public static Endereco create(Long idEntidade, TipoEndereco tipoEndereco, String rua, String cep, String cidade, String bairro, String complemento, String numero) {
        Endereco endereco = new Endereco();
        endereco.setIdEntidade(idEntidade);
        endereco.setTipoEndereco(tipoEndereco);
        endereco.setRua(rua);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);

        return endereco;
    }

    public static Endereco create(Long id, Long idEntidade, TipoEndereco tipoEndereco, String rua, String cep, String cidade, String bairro, String complemento, String numero) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setIdEntidade(idEntidade);
        endereco.setTipoEndereco(tipoEndereco);
        endereco.setRua(rua);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);

        return endereco;
    }
}
