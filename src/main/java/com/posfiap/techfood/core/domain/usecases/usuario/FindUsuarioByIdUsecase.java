package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;

import java.util.Optional;

public class FindUsuarioByIdUsecase {
    private IUsuarioGateway usuarioGateway;

    private FindUsuarioByIdUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static FindUsuarioByIdUsecase create(IUsuarioGateway usuarioGateway) {
        return new FindUsuarioByIdUsecase(usuarioGateway);
    }

    public Usuario run(Long id) {
        Optional<Usuario> usuario = this.usuarioGateway.findById(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario do id " + id + " não encontrado na base dados");
        }

        return usuario.get();
    }
}
