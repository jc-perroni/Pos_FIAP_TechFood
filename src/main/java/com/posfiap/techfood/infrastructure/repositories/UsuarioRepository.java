package com.posfiap.techfood.infrastructure.repositories;

import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Page<Usuario> findAllByPerfilIn(List<PerfilUsuario> perfil, Pageable pageable);

    Optional<Usuario> findByIdAndPerfilIn(Long id, List<PerfilUsuario> perfil);
}
