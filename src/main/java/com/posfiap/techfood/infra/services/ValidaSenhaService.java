package com.posfiap.techfood.infra.services;

import com.posfiap.techfood.infra.models.Usuario;
import com.posfiap.techfood.infra.utils.HashPassword;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValidaSenhaService {

    ValidaSenhaService(){};

    public static boolean autenticarSenha(String password, Usuario usuario){
        log.info("Verificação de senha...");
        return HashPassword.verifyPassword(password, usuario.getPassword());

    }
}
