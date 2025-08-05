package com.posfiap.techfood.infrastructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface HashPassword {

    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    static String hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}


