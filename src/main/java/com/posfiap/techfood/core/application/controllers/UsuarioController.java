package com.posfiap.techfood.core.application.controllers;

import com.posfiap.techfood.core.application.dto.LoginDTO;
import com.posfiap.techfood.core.application.dto.NovoUsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.application.dto.UsuarioUpdateDto;
import com.posfiap.techfood.core.application.gateways.UsuarioGatewayImp;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioDataSource;
import com.posfiap.techfood.core.application.presenters.UsuarioPresenter;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.usecases.usuario.*;
import com.posfiap.techfood.core.domain.exceptions.ResourceNotFoundException;

import java.util.List;

public class UsuarioController {
    private final IUsuarioDataSource dataSource;

    private UsuarioController(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsuarioController create(IUsuarioDataSource dataSource) {
        return new UsuarioController(dataSource);
    }

    public List<UsuarioDTO> findAllUsuarios(int page, int size) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = FindAllUsuariosUsecase.create(usuarioGateway);

        try {
            List<Usuario> usuarioList = useCase.run(page, size);
            return usuarioList.stream().map(
                    UsuarioPresenter::toDTO).toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuarios: " + e.getMessage());
        }
    }

    public UsuarioDTO findUsuarioById(Long id) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = FindUsuarioByIdUsecase.create(usuarioGateway);

        try {
            var usuario = useCase.run(id);
            return UsuarioPresenter.toDTO(usuario);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuario do id "  + id + " não existe");
        }
    }

    public UsuarioDTO inserirUsuario(NovoUsuarioDTO novoUsuarioDTO) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = InsertUsuarioUsecase.create(usuarioGateway);

        try {
            Usuario usuario = useCase.run(novoUsuarioDTO);
            return UsuarioPresenter.toDTO(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuario: " + e.getMessage());
        }
    }

    public UsuarioDTO atualizarUsuario(UsuarioUpdateDto usuarioDTO, Long id) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = UpdateUsuarioUsecase.create(usuarioGateway);

        try {
            Usuario usuario = useCase.run(usuarioDTO, id);
            return UsuarioPresenter.toDTO(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuario: " + e.getMessage());
        }
    }

    public Integer excluirUsuario(Long id) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = DeleteUsuarioUsecase.create(usuarioGateway);

        try {
            return useCase.run(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuario: " + e.getMessage());
        }
    }

    public void validarLogin(LoginDTO loginDTO) {
        var usuarioGateway = UsuarioGatewayImp.create(dataSource);
        var useCase = ValidarLoginUsecase.create(usuarioGateway);

        useCase.run(loginDTO);
    }
}
