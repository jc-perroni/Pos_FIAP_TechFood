package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.InvalidPasswordException;
import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Usuario;
import com.posfiap.techfood.models.enums.PerfilUsuario;
import com.posfiap.techfood.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.posfiap.techfood.services.ValidaSenhaService.autenticarSenha;
import static com.posfiap.techfood.utils.HashPassword.hashPassword;

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
