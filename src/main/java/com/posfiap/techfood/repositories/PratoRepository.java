package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {
}
