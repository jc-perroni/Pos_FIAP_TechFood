package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.interfaces.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.infra.models.dto.UsuarioEntidadeDTO;

import java.util.Optional;

public class InsertUsuarioUsecase {
    private IUsuarioGateway usuarioGateway;

    private InsertUsuarioUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static InsertUsuarioUsecase create(IUsuarioGateway usuarioGateway) {
        return new InsertUsuarioUsecase(usuarioGateway);
    }

    public Usuario run(NovoUsuarioDTO clienteDTO) {
        Optional usuarioExistente = this.usuarioGateway.findByEmail(clienteDTO.email());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioJaExistenteException("Usuario com email: " + clienteDTO.email() + " ja existe");
        }

        usuarioExistente = this.usuarioGateway.findByUserame(clienteDTO.username());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioJaExistenteException("Usuario com username: " + clienteDTO.username() + " ja existe");
        }

        final Usuario novoUsuario = Usuario.create(
                clienteDTO.tipoDeUsuario(),
                clienteDTO.nome(),
                clienteDTO.email(),
                clienteDTO.telefone(),
                clienteDTO.cpf(),
                clienteDTO.username(),
                clienteDTO.password()
        );

        return this.usuarioGateway.save(novoUsuario);
    }
}
