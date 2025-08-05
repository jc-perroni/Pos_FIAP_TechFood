package com.posfiap.techfood.core.application.interfaces.usuario;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDataSource {
    Optional<UsuarioDTO> findById(Long id);
    Optional<UsuarioDTO> findByEmail(String email);
    Optional<UsuarioDTO> findByUserame(String username);
    List<UsuarioDTO> findAll(int size, int offset);
    UsuarioDTO update(UsuarioDTO usuario, long id);
    UsuarioDTO save(NovoUsuarioDTO usuario);
    Integer delete(long id);
    Boolean verificarSenha(String senhaInformada, String senhaUsuario);
}
