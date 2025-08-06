package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.gateways.EnderecoGatewayImp;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;
import com.posfiap.techfood.core.application.presenters.EnderecoPresenter;
import com.posfiap.techfood.core.domain.entities.Endereco;
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

    public List<EnderecoComIdDTO> findAllEnderecos(int page, int size) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = FindAllEnderecosUsecase.create(enderecoGateway);

        try {
            List<Endereco> enderecoList = useCase.run(page, size);
            return enderecoList.stream().map(
                    EnderecoPresenter::toDTO).toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar enderecos: " + e.getMessage());
        }
    }

    public EnderecoComIdDTO findEnderecoById(Long id) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = FindEnderecoByIdUsecase.create(enderecoGateway);

        try {
            var endereco = useCase.run(id);
            return EnderecoPresenter.toDTO(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereco: " + e.getMessage());
        }
    }

    public EnderecoComIdDTO inserirEndereco(NovoEnderecoDTO novoEnderecoDTO) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = InsertEnderecoUsecase.create(enderecoGateway);

        try {
            Endereco endereco = useCase.run(novoEnderecoDTO);
            return EnderecoPresenter.toDTO(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar endereco: " + e.getMessage());
        }

    }

    public EnderecoComIdDTO atualizarEndereco(NovoEnderecoDTO enderecoDTO, Long id) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = UpdateEnderecoUsecase.create(enderecoGateway);

        try {
            Endereco endereco = useCase.run(enderecoDTO, id);
            return EnderecoPresenter.toDTO(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar endereco: " + e.getMessage());
        }

    }

    public Integer excluirEndereco(Long id) {
        var enderecoGateway = EnderecoGatewayImp.create(dataSource);
        var useCase = DeleteEnderecoUsecase.create(enderecoGateway);

        try {
            return useCase.run(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir endereco: " + e.getMessage());
        }
    }
}
