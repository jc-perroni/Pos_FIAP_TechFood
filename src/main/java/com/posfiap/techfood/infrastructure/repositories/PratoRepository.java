package com.posfiap.techfood.infrastructure.repositories;

import com.posfiap.techfood.infrastructure.models.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {
}
