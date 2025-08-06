package com.posfiap.techfood.core.application.gateways;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoGateway;
import com.posfiap.techfood.core.domain.entities.Endereco;

import java.util.List;
import java.util.Optional;

public class EnderecoGatewayImp implements IEnderecoGateway {
    private final IEnderecoDataSource dataSource;

    private EnderecoGatewayImp (IEnderecoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EnderecoGatewayImp create(IEnderecoDataSource dataSource) {
        return new EnderecoGatewayImp(dataSource);
    }

    @Override
    public Optional<Endereco> findById(Long id) {
       Optional<EnderecoComIdDTO> enderecoDtoOptional = this.dataSource.findById(id);
        return enderecoDtoOptional.map(EnderecoGatewayImp::toEntity);
    }

    @Override
    public List<Endereco> findAll(int page, int size) {
        List<EnderecoComIdDTO> enderecoComIdDtoList = this.dataSource.findAll(page, size);
        return enderecoComIdDtoList.stream().map(EnderecoGatewayImp::toEntity).toList();
    }

    @Override
    public Endereco update(Endereco endereco, long id) {
        EnderecoComIdDTO enderecoComIdDTO = toEnderecoDTO(endereco);
        return toEntity(this.dataSource.update(enderecoComIdDTO, id));
    }

    @Override
    public Endereco save(Endereco endereco) {
        NovoEnderecoDTO novoEnderecoDTO = toNovoEnderecoDto(endereco);
        return toEntity(this.dataSource.save(novoEnderecoDTO));
    }

    @Override
    public Integer delete(long id) {
        return this.dataSource.delete(id);
    }

    private static Endereco toEntity(EnderecoComIdDTO enderecoComIdDto) {
        return Endereco.create(
                enderecoComIdDto.id(),
                enderecoComIdDto.idEntidade(),
                enderecoComIdDto.tipoEndereco(),
                enderecoComIdDto.rua(),
                enderecoComIdDto.cep(),
                enderecoComIdDto.cidade(),
                enderecoComIdDto.bairro(),
                enderecoComIdDto.complemento(),
                enderecoComIdDto.numero()
        );
    }

    private static EnderecoComIdDTO toEnderecoDTO(Endereco endereco) {
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

    private static NovoEnderecoDTO toNovoEnderecoDto(Endereco endereco) {
        return new NovoEnderecoDTO(
                endereco.getIdEntidade(),
                endereco.getTipoEndereco(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero());
    }
}
