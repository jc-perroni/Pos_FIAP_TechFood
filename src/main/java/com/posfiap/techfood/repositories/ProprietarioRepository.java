package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Optional<Proprietario> findByUsername(String username);
}
