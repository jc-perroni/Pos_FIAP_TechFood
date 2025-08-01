package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.gateways.EnderecoGatewayImp;
import com.posfiap.techfood.core.application.interfaces.IEnderecoDataSource;
import com.posfiap.techfood.core.application.presenters.EnderecoPresenter;
import com.posfiap.techfood.core.domain.entities.Endereco;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.core.domain.usecases.endereco.InsertEnderecoUsecase;

import java.util.List;

public class EnderecoController {
    private final IEnderecoDataSource dataSource;

    private EnderecoController(IEnderecoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EnderecoController create(IEnderecoDataSource dataSource) {
        return new EnderecoController(dataSource);
    }

    public void findAllEnderecos(int size, int offset) {
    }

    public void findEnderecoByID(Long id) {

    }

    public EnderecoDTO inserirEndereco(NovoEnderecoDTO novoEnderecoDTO) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = InsertEnderecoUsecase.create(enderecoGateway);

        try {
            Endereco endereco = useCase.run(novoEnderecoDTO);
            return EnderecoPresenter.toDTO(endereco);
        } catch (UsuarioJaExistenteException e) {
            return  null;
        }

    }

    public void atualizarEndereco(EnderecoDTO enderecoDTO) {

    }

    public void excluirEndereco(Long id) {

    }
}
