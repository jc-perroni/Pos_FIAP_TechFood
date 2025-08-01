package com.posfiap.techfood.core.domain.usecases.endereco;

import com.posfiap.techfood.core.application.interfaces.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;

import java.util.Optional;

public class DeleteEnderecoUsecase {
    private final IEnderecoGateway enderecoGateway;

    private DeleteEnderecoUsecase(IEnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public static DeleteEnderecoUsecase create(IEnderecoGateway enderecoGateway) {
        return new DeleteEnderecoUsecase(enderecoGateway);
    }

    public Integer run(Long id) {
        Optional<Endereco> endereco = this.enderecoGateway.findById(id);
        if (endereco.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario do id " + id + " não encontrado na base dados");
        }

        return this.enderecoGateway.delete(id);
    }
}
