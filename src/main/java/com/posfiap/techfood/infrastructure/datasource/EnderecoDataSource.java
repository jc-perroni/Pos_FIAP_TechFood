package com.posfiap.techfood.infrastructure.datasource;

import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.core.application.interfaces.endereco.IEnderecoDataSource;

import java.util.List;
import java.util.Optional;

public class EnderecoDataSource implements IEnderecoDataSource {

    @Override
    public Optional<EnderecoDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<EnderecoDTO> findAll(int size, int offset) {
        return List.of();
    }

    @Override
    public EnderecoDTO update(EnderecoDTO enderecoDTO, long id) {
        return null;
    }

    @Override
    public EnderecoDTO save(NovoEnderecoDTO novoEnderecoDTO) {
        return null;
    }

    @Override
    public Integer delete(long id) {
        return 0;
    }
}
