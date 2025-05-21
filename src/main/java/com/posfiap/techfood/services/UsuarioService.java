package com.posfiap.techfood.services;

import com.posfiap.techfood.models.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.posfiap.techfood.services.ValidaSenhaService.autenticarSenha;
import static com.posfiap.techfood.utils.HashPassword.hashPassword;

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
            log.warn("Senha incorreta.");
        }
    }
}
