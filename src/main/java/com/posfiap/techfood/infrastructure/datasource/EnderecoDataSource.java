package com.posfiap.techfood.infrastructure.datasource;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;
import com.posfiap.techfood.infrastructure.models.Endereco;
import com.posfiap.techfood.infrastructure.models.dto.endereco.EnderecoDTO;
import com.posfiap.techfood.infrastructure.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnderecoDataSource implements IEnderecoDataSource {

    private final EnderecoService enderecoService;

    @Override
    public Optional<EnderecoComIdDTO> findById(Long id) {
        Endereco endereco = enderecoService.findEnderecoById(id);
        return Optional.of(toDto(endereco));
    }

    @Override
    public List<EnderecoComIdDTO> findAll(int page, int size) {
        List<Endereco> enderecos = enderecoService.findAllEnderecos(page, size).getContent();
        return enderecos.stream().map(EnderecoDataSource::toDto).toList();
    }

    @Override
    public EnderecoComIdDTO update(EnderecoComIdDTO enderecoComIdDTO, long id) {
        Endereco endereco = setEndereco(enderecoComIdDTO);
        enderecoService.updateEndereco(endereco, id);
        return enderecoComIdDTO;
    }

    @Override
    public EnderecoComIdDTO save(NovoEnderecoDTO novoEnderecoDTO) {
        EnderecoDTO enderecoDto = novoEnderecoDtoToEnderecoDto(novoEnderecoDTO);
        enderecoService.insertEndereco(enderecoDto);
        return toDto(novoEnderecoDTO);
    }

    @Override
    public Integer delete(long id) {
        enderecoService.deleteEndereco(id);
        return 1;
    }

    private static EnderecoComIdDTO toDto(Endereco endereco) {
        return new EnderecoComIdDTO(
                endereco.getId(),
                endereco.getUsuario().getId(),
                endereco.getTipoEndereco(),
                endereco.getRua(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }

    private static EnderecoDTO novoEnderecoDtoToEnderecoDto(NovoEnderecoDTO novoEnderecoDTO) {
        return new EnderecoDTO(
                novoEnderecoDTO.idEntidade(),
                novoEnderecoDTO.tipoEndereco(),
                novoEnderecoDTO.rua(),
                novoEnderecoDTO.cep(),
                novoEnderecoDTO.cidade(),
                novoEnderecoDTO.bairro(),
                novoEnderecoDTO.complemento(),
                novoEnderecoDTO.numero()
        );
    }

    private static EnderecoComIdDTO toDto(NovoEnderecoDTO novoEnderecoDTO) {
        return new EnderecoComIdDTO(
                null,
                novoEnderecoDTO.idEntidade(),
                novoEnderecoDTO.tipoEndereco(),
                novoEnderecoDTO.rua(),
                novoEnderecoDTO.cep(),
                novoEnderecoDTO.cidade(),
                novoEnderecoDTO.bairro(),
                novoEnderecoDTO.complemento(),
                novoEnderecoDTO.numero()
        );
    }


    private static Endereco setEndereco(EnderecoComIdDTO enderecoComIdDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoComIdDTO.rua());
        endereco.setCep(enderecoComIdDTO.cep());
        endereco.setCidade(enderecoComIdDTO.cidade());
        endereco.setBairro(enderecoComIdDTO.bairro());
        endereco.setComplemento(enderecoComIdDTO.complemento());
        endereco.setNumero(enderecoComIdDTO.numero());

        return endereco;
    }
}
