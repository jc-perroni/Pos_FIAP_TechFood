package com.posfiap.techfood.core.application.interfaces.usuario;

import com.posfiap.techfood.core.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioGateway<T> {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUserame(String username);
    List<Usuario> findAll(int size, int offset);
    Usuario update(Usuario usuario, long id);
    Usuario save(Usuario usuario);
    Integer delete(long id);
    Boolean verificarSenha(String senhaInformada, String senhaUsuario);
}
