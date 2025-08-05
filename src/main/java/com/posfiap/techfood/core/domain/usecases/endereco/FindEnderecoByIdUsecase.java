package com.posfiap.techfood.core.domain.usecases.endereco;

import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;
import com.posfiap.techfood.core.domain.exceptions.EnderecoNaoEncontradoException;

import java.util.Optional;

public class FindEnderecoByIdUsecase {
    private final IEnderecoGateway enderecoGateway;

    private FindEnderecoByIdUsecase(IEnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public static FindEnderecoByIdUsecase create(IEnderecoGateway enderecoGateway) {
        return new FindEnderecoByIdUsecase(enderecoGateway);
    }

    public Endereco run(Long id) {
        Optional<Endereco> endereco = this.enderecoGateway.findById(id);
        if (endereco.isEmpty()) {
            throw new EnderecoNaoEncontradoException("O endereço do id " + id + " não foi encontrado na base dados");
        }

        return endereco.get();
    }
}
