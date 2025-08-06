package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;

import java.util.Optional;

public class InsertUsuarioUsecase {
    private IUsuarioGateway usuarioGateway;

    private InsertUsuarioUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static InsertUsuarioUsecase create(IUsuarioGateway usuarioGateway) {
        return new InsertUsuarioUsecase(usuarioGateway);
    }

    public Usuario run(NovoUsuarioDTO usuarioDTO) {
        Optional usuarioExistente = this.usuarioGateway.findByEmail(usuarioDTO.email());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioJaExistenteException("Usuario com email: " + usuarioDTO.email() + " ja existe");
        }

        usuarioExistente = this.usuarioGateway.findByUserame(usuarioDTO.username());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioJaExistenteException("Usuario com username: " + usuarioDTO.username() + " ja existe");
        }

        final Usuario novoUsuario = Usuario.create(
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.telefone(),
                usuarioDTO.cpf(),
                usuarioDTO.username(),
                usuarioDTO.password()
        );

        return this.usuarioGateway.save(novoUsuario);
    }
}
