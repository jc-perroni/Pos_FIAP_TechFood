package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.UsuarioUpdateDto;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;

import java.util.Optional;

public class UpdateUsuarioUsecase {
    private IUsuarioGateway usuarioGateway;

    private UpdateUsuarioUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static UpdateUsuarioUsecase create(IUsuarioGateway usuarioGateway) {
        return new UpdateUsuarioUsecase(usuarioGateway);
    }

    public Usuario run(UsuarioUpdateDto usuarioDTO, Long id) {
        Optional<Usuario> usuarioExistente = this.usuarioGateway.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario do id "  + id + " não existe");
        }

        final Usuario usuario = Usuario.create(
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.telefone(),
                usuarioDTO.cpf(),
                usuarioExistente.get().getUsername(),
                usuarioExistente.get().getPassword()
        );

        return this.usuarioGateway.update(usuario, id);
    }
}
