package com.posfiap.techfood.infrastructure.repositories;

import com.posfiap.techfood.infrastructure.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}