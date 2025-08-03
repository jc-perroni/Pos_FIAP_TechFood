package com.posfiap.techfood.core.domain.usecases.usuario;

import com.posfiap.techfood.core.application.dto.LoginDTO;
import com.posfiap.techfood.core.application.interfaces.usuario.IUsuarioGateway;
import com.posfiap.techfood.core.domain.entities.Usuario;
import com.posfiap.techfood.core.domain.exceptions.SenhaInvalidaException;
import com.posfiap.techfood.core.domain.exceptions.UsuarioNaoEncontradoException;

import java.util.Optional;

public class ValidarLoginUsecase {

    private IUsuarioGateway usuarioGateway;

    private ValidarLoginUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static ValidarLoginUsecase create(IUsuarioGateway usuarioGateway) {
        return new ValidarLoginUsecase(usuarioGateway);
    }

    public void run(LoginDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = this.usuarioGateway.findByUserame(usuarioDTO.getUsuario());
        if (usuarioExistente.isEmpty()) {
            throw new UsuarioNaoEncontradoException("O usuario "  + usuarioDTO.getUsuario() + " não existe");
        }

        boolean senhaValida = this.usuarioGateway.verificarSenha(
                usuarioDTO.getSenha(), usuarioExistente.get().getPassword());

        if (!senhaValida) {
            throw new SenhaInvalidaException("Senha invalida");
        }
    }
}
