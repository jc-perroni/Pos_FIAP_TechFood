package com.posfiap.techfood.core.domain.usecases.endereco;

import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;
import com.posfiap.techfood.core.domain.exceptions.EnderecoNaoEncontradoException;

import java.util.Optional;

public class UpdateEnderecoUsecase {
    private final IEnderecoGateway enderecoGateway;

    private UpdateEnderecoUsecase(IEnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public static UpdateEnderecoUsecase create(IEnderecoGateway enderecoGateway) {
        return new UpdateEnderecoUsecase(enderecoGateway);
    }

    public Endereco run(NovoEnderecoDTO enderecoDTO, long id) {
        Optional<Endereco> enderecoExistente = this.enderecoGateway.findById(id);
        if (enderecoExistente.isEmpty()) {
            throw new EnderecoNaoEncontradoException("O endereço do id "+ id + " não foi encontrado na base dados");
        }

        final Endereco novoEndereco = Endereco.create(
                enderecoDTO.idEntidade(),
                enderecoDTO.tipoEndereco(),
                enderecoDTO.rua(),
                enderecoDTO.cep(),
                enderecoDTO.cidade(),
                enderecoDTO.bairro(),
                enderecoDTO.complemento(),
                enderecoDTO.numero()
        );

        return this.enderecoGateway.update(novoEndereco, id);
    }
}
