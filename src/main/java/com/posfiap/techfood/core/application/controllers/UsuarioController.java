package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.LoginDTO;
import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.gateways.UsuarioGatewayImp;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.core.application.presenters.UsuarioPresenter;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.UsuarioJaExistenteException;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;
import com.posfiap.techfood.core.domain.usecases.usuario.*;

import java.util.List;

public class UsuarioController {
    private final IUsuarioDataSource dataSource;

    private UsuarioController(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsuarioController create(IUsuarioDataSource dataSource) {
        return new UsuarioController(dataSource);
    }

    public List<UsuarioDTO> findAllUsuarios(int size, int offset) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = FindAllUsuariosUsecase.create(usuarioGateway);

        try {
            List<Usuario> usuarioList = useCase.run(size, offset);
            return usuarioList.stream().map(
                    UsuarioPresenter::toDTO).toList();
        } catch (Exception e) {
            return null;
        }
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

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = UpdateUsuarioUsecase.create(usuarioGateway);

        try {
            Usuario usuario = useCase.run(usuarioDTO);
            return UsuarioPresenter.toDTO(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return  null;
        }
    }

    public Integer excluirUsuario(Long id) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = DeleteUsuarioUsecase.create(usuarioGateway);

        try {
            return useCase.run(id);
        } catch (UsuarioNaoEncontradoException e) {
            return null;
        }
    }

    public void validarLogin(LoginDTO loginDTO) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = ValidarLoginUsecase.create(usuarioGateway);

        useCase.run(loginDTO);
    }
}
