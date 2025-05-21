package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.dto.LoginDTO;
import com.posfiap.techfood.models.dto.UsuarioDTO;
import com.posfiap.techfood.services.ValidaLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/login")
@RestController
public class LoginController {

    private final ValidaLoginService validaUsuario;

    @PostMapping
    public ResponseEntity<UsuarioDTO> validarLogin(@RequestBody LoginDTO loginDTO){
        log.info("Validando login...");
        try {
            return ResponseEntity.ok(validaUsuario.validarLogin(loginDTO));
        }
        catch (RuntimeException e){
            log.info("Senha incorreta.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        }
    }

