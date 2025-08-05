package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.domain.entities.Endereco;

public class EnderecoPresenter {
    public static EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getIdEntidade(),
                endereco.getTipoEndereco(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );

    }
}
