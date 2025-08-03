package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.dto.cliente.ClienteLoginDTO;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioLoginDTO;
import com.posfiap.techfood.models.dto.usuario.UsuarioDTO;
import com.posfiap.techfood.services.ValidaLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login", description = "Autenticação de usuários")
public class LoginController {

    private final ValidaLoginService validaUsuario;

    @Operation(summary = "Autenticar cliente")
    @PostMapping("/cliente")
    public ResponseEntity<UsuarioDTO> autenticarCliente(@RequestBody ClienteLoginDTO loginDTO) {
        log.info("Validando login...");
        try {
            return ResponseEntity.ok(validaUsuario.validarLogin(loginDTO));
        } catch (RuntimeException e) {
            log.error("Não foi possível realizar o login.", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Autenticar proprietário")
    @PostMapping("/proprietario")
    public ResponseEntity<UsuarioDTO> autenticarProprietario(@RequestBody ProprietarioLoginDTO loginDTO) {
        log.info("Validando login...");
        try {
            return ResponseEntity.ok(validaUsuario.validarLogin(loginDTO));
        } catch (RuntimeException e) {
            log.error("Não foi possível realizar o login.", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
