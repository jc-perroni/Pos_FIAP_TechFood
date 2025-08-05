package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.usuario.AlteracaoSenhaDTO;
import com.posfiap.techfood.infrastructure.repositories.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlteracaoSenhaService {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public void alterarSenha(@NotNull AlteracaoSenhaDTO alteracaoSenhaDTO) {
        Usuario usuario = validarUsuario(alteracaoSenhaDTO);
        usuarioService.alterarSenha(alteracaoSenhaDTO.getSenhaAntiga(), alteracaoSenhaDTO.getSenhaNova(), usuario);
        usuarioRepository.save(usuario);
    }

    private Usuario validarUsuario(AlteracaoSenhaDTO alteracaoSenhaDTO) {
        return usuarioRepository.findByUsername(alteracaoSenhaDTO.getUsername())
                .orElseThrow(() -> new InvalidUserNameAccount("Usuário não localizado."));
    }
}