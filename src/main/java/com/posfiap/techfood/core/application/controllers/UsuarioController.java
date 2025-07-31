package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.gateways.UsuarioGatewayImp;
import com.posfiap.techfood.core.application.interfaces.IUsuarioDataSource;
import com.posfiap.techfood.core.application.presenters.UsuarioPresenter;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;
import com.posfiap.techfood.core.domain.usecases.usuario.FindUsuarioByIdUsecase;
import com.posfiap.techfood.core.domain.usecases.usuario.InsertUsuarioUsecase;

public class UsuarioController {
    private final IUsuarioDataSource dataSource;

    private UsuarioController(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsuarioController create(IUsuarioDataSource dataSource) {
        return new UsuarioController(dataSource);
    }

    public void findAllUsuarios() {

    }

    public UsuarioDTO findUsuarioById(Long id) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = FindUsuarioByIdUsecase.create(usuarioGateway);

        try {
            var usuario = useCase.run(id);
            return UsuarioPresenter.toDTO(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return null;
        }
    }

    public UsuarioDTO inserirUsuario(NovoUsuarioDTO novoUsuarioDTO) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = InsertUsuarioUsecase.create(usuarioGateway);

        try {
            Usuario usuario = useCase.run(novoUsuarioDTO);
            return UsuarioPresenter.toDTO(usuario);
        } catch (UsuarioJaExistenteException e) {
            return  null;
        }
    }

    public void atualizarUsuario() {

    }

    public void excluirUsuario() {

    }
}
