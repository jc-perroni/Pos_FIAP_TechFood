package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.infrastructure.models.dto.usuario.AlterarPerfilDTO;
import com.posfiap.techfood.infrastructure.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Operation(summary = "Alterar tipo do Perfil (Cliente/Proprietário/Ambos)")
    @PostMapping("/alterar-perfil")
    public ResponseEntity<Void> alterarPerfil(
            @Valid @RequestBody AlterarPerfilDTO dto) {
        usuarioService.alterarPerfil(dto.idUsuario(), dto.novoPerfil());
        return ResponseEntity.noContent().build();
    }
}
