package com.posfiap.techfood.infrastructure.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashPasswordTest {

    @Test
    void testHashPasswordAndVerify() {
        String rawPassword = "minhaSenhaSecreta123";
        String hashedPassword = HashPassword.hashPassword(rawPassword);

        // A senha hashed deve ser verificável com a senha original
        assertTrue(HashPassword.verifyPassword(rawPassword, hashedPassword));

        // A senha hashed não deve ser verificável com uma senha incorreta
        assertFalse(HashPassword.verifyPassword("senhaIncorreta", hashedPassword));
    }

    @Test
    void testVerifyPassword_IncorrectPassword() {
        String rawPassword = "outraSenha";
        String hashedPassword = HashPassword.hashPassword(rawPassword);

        assertFalse(HashPassword.verifyPassword("senhaErrada", hashedPassword));
    }

    @Test
    void testVerifyPassword_DifferentHash() {
        String rawPassword1 = "senha1";
        String hashedPassword1 = HashPassword.hashPassword(rawPassword1);

        String rawPassword2 = "senha2";
        String hashedPassword2 = HashPassword.hashPassword(rawPassword2);

        assertFalse(HashPassword.verifyPassword(rawPassword1, hashedPassword2));
    }
}


