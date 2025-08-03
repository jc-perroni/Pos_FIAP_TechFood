package com.posfiap.techfood.repositories;

import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByUsername(String username);

}
