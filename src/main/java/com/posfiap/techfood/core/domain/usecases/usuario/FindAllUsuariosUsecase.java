package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;

import java.util.List;

public class FindAllUsuariosUsecase {
    private IUsuarioGateway usuarioGateway;

    private FindAllUsuariosUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static FindAllUsuariosUsecase create(IUsuarioGateway usuarioGateway) {
        return new FindAllUsuariosUsecase(usuarioGateway);
    }

    public List<Usuario> run(int size, int offset) {
        return this.usuarioGateway.findAll(size, offset);
    }

}
