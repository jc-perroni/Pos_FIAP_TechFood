package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.utils.HashPassword;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValidaSenhaService {

    ValidaSenhaService(){};

    public static boolean autenticarSenha(String password, Usuario usuario){
        log.info("Verificação de senha...");
        return HashPassword.verifyPassword(password, usuario.getPassword());

    }
}
