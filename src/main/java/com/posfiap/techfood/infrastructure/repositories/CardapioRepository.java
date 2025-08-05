package com.posfiap.techfood.infrastructure.repositories;

import com.posfiap.techfood.infrastructure.models.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository  extends JpaRepository<Cardapio, Long> {
}
