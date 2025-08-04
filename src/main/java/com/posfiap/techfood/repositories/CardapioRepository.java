package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository  extends JpaRepository<Cardapio, Long> {
}
