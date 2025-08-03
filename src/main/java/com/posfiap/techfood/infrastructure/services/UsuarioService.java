package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidPasswordException;
import com.posfiap.techfood.infrastructure.models.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.posfiap.techfood.infrastructure.services.ValidaSenhaService.autenticarSenha;
import static com.posfiap.techfood.infrastructure.utils.HashPassword.hashPassword;

@Slf4j
@Service
public class UsuarioService {

    void alterarSenha(String senha, Usuario usuario) {
            usuario.criarConta(hashPassword(senha));
    }

    void alterarSenha(String senhaAntiga, String senhaNova, Usuario usuario) {
        if(autenticarSenha(senhaAntiga, usuario)){
            usuario.alterarSenha(hashPassword(senhaNova));
        }
        else {
            throw new InvalidPasswordException("Senha invalida");
        }
    }
}
