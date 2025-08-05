package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidPasswordException;
import com.posfiap.techfood.infrastructure.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import com.posfiap.techfood.infrastructure.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.posfiap.techfood.infrastructure.services.ValidaSenhaService.autenticarSenha;
import static com.posfiap.techfood.infrastructure.utils.HashPassword.hashPassword;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void alterarSenha(String senha, Usuario usuario) {
            usuario.criarConta(hashPassword(senha));
    }

    public void alterarSenha(String senhaAntiga, String senhaNova, Usuario usuario) {
        if(autenticarSenha(senhaAntiga, usuario)){
            usuario.alterarSenha(hashPassword(senhaNova));
        }
        else {
            throw new InvalidPasswordException("Senha invalida");
        }
    }

    public void alterarPerfil(Long id, PerfilUsuario perfil) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não localizado para alteração de perfil."));
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);
    }

}
