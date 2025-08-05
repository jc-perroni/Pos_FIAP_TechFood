package com.posfiap.techfood.core.application.interfaces.endereco;

import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;

import java.util.List;
import java.util.Optional;

public interface IEnderecoDataSource {
    Optional<EnderecoComIdDTO> findById(Long id);
    List<EnderecoComIdDTO> findAll(int size, int offset);
    EnderecoComIdDTO update(EnderecoComIdDTO enderecoComIdDTO, long id);
    EnderecoComIdDTO save(NovoEnderecoDTO novoEnderecoDTO);
    Integer delete(long id);
}
