package com.posfiap.techfood.core.domain.usecases.endereco;

import com.posfiap.techfood.core.application.interfaces.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;

import java.util.List;

public class FindAllEnderecosUsecase {
    private final IEnderecoGateway enderecoGateway;

    private FindAllEnderecosUsecase(IEnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    public static FindAllEnderecosUsecase create(IEnderecoGateway enderecoGateway) {
        return new FindAllEnderecosUsecase(enderecoGateway);
    }

    public List<Endereco> run(int size, int offset) {
        return this.enderecoGateway.findAll(size, offset);
    }
}
