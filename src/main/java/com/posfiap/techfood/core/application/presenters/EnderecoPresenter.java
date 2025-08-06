package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.domain.entities.Endereco;

public class EnderecoPresenter {
    public static EnderecoComIdDTO toDTO(Endereco endereco) {
        return new EnderecoComIdDTO(
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
