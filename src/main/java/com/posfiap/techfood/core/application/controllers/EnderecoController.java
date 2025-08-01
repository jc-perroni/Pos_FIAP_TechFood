package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.gateways.EnderecoGatewayImp;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;
import com.posfiap.techfood.core.application.presenters.EnderecoPresenter;
import com.posfiap.techfood.core.domain.entities.Endereco;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;
import com.posfiap.techfood.core.domain.usecases.endereco.*;

import java.util.List;

public class EnderecoController {
    private final IEnderecoDataSource dataSource;

    private EnderecoController(IEnderecoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EnderecoController create(IEnderecoDataSource dataSource) {
        return new EnderecoController(dataSource);
    }

    public List<EnderecoDTO> findAllEnderecos(int size, int offset) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = FindAllEnderecosUsecase.create(enderecoGateway);

        try {
            List<Endereco> enderecoList = useCase.run(size, offset);
            return enderecoList.stream().map(
                    EnderecoPresenter::toDTO).toList();
        } catch (Exception e) {
            return null;
        }
    }

    public EnderecoDTO findEnderecoByID(Long id) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = FindEnderecoByIdUsecase.create(enderecoGateway);

        try {
            var endereco = useCase.run(id);
            return EnderecoPresenter.toDTO(endereco);
        } catch (UsuarioNaoEncontradoException e) {
            return null;
        }
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

    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = UpdateEnderecoUsecase.create(enderecoGateway);

        try {
            Endereco endereco = useCase.run(enderecoDTO);
            return EnderecoPresenter.toDTO(endereco);
        } catch (UsuarioNaoEncontradoException e) {
            return  null;
        }

    }

    public Integer excluirEndereco(Long id) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = DeleteEnderecoUsecase.create(enderecoGateway);

        try {
            return useCase.run(id);
        } catch (UsuarioNaoEncontradoException e) {
            return null;
        }
    }
}
