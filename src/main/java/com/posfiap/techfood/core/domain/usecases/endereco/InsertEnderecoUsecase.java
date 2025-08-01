package com.posfiap.techfood.core.domain.usecases.endereco;

import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;

public class InsertEnderecoUsecase {
    private final IEnderecoGateway enderecoGateway;

    private InsertEnderecoUsecase(IEnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public static InsertEnderecoUsecase create(IEnderecoGateway enderecoGateway) {
        return new InsertEnderecoUsecase(enderecoGateway);
    }

    public Endereco run(NovoEnderecoDTO novoEnderecoDTO) {
        final Endereco novoEndereco = Endereco.create(
                novoEnderecoDTO.idEntidade(),
                novoEnderecoDTO.tipoEndereco(),
                novoEnderecoDTO.rua(),
                novoEnderecoDTO.cep(),
                novoEnderecoDTO.cidade(),
                novoEnderecoDTO.bairro(),
                novoEnderecoDTO.complemento(),
                novoEnderecoDTO.numero()
        );

        return this.enderecoGateway.save(novoEndereco);
    }
}
