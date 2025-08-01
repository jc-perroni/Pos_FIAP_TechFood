package com.posfiap.techfood.core.application.interfaces;

import com.posfiap.techfood.core.domain.entities.Endereco;

import java.util.List;
import java.util.Optional;

public interface IEnderecoGateway {
    Optional<Endereco> findById(Long id);
    List<Endereco> findAll(int size, int offset);
    Endereco update(Endereco endereco, long id);
    Endereco save(Endereco endereco);
    Integer delete(long id);
}

