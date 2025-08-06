package com.posfiap.techfood.core.application.presenters;

import com.posfiap.techfood.core.application.dto.UsuarioDTO;
import com.posfiap.techfood.core.domain.entities.Usuario;

public class UsuarioPresenter {
    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getTelefone(),
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getUsername(),
                "***",
                usuario.getDataCriacaoConta(),
                usuario.getDataAlteracaoSenha(),
                usuario.getDataAlteracaoSenha(),
                usuario.getPerfil()
        );
    }

}
