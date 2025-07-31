package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
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

    public Usuario run(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = this.usuarioGateway.findById(usuarioDTO.id());
        if (usuarioExistente.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario do id "  + usuarioDTO.id() + " não existe");
        }

        // "Tipo de usuario" não poderá ser modificado pelo put
        final Usuario usuario = Usuario.create(
                usuarioExistente.get().getTipoUsuario(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.telefone(),
                usuarioDTO.cpf(),
                usuarioDTO.username(),
                usuarioDTO.password()
        );

        return this.usuarioGateway.update(usuario, usuarioDTO.id());
    }
}
