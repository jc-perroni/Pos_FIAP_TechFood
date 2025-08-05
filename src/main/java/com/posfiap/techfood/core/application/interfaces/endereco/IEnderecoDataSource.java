package com.posfiap.techfood.core.application.interfaces.endereco;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;

import java.util.List;
import java.util.Optional;

public interface IEnderecoDataSource {
    Optional<EnderecoDTO> findById(Long id);
    List<EnderecoDTO> findAll(int size, int offset);
    EnderecoDTO update(EnderecoDTO enderecoDTO, long id);
    EnderecoDTO save(NovoEnderecoDTO novoEnderecoDTO);
    Integer delete(long id);
}
