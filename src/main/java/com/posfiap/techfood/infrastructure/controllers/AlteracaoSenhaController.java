package com.posfiap.techfood.infrastructure.controllers;

import com.posfiap.techfood.infrastructure.models.dto.AlteracaoSenhaDTO;
import com.posfiap.techfood.infrastructure.models.dto.ClienteAlteracaoSenhaDTO;
import com.posfiap.techfood.infrastructure.models.dto.ProprietarioAlteracaoSenhaDTO;
import com.posfiap.techfood.infrastructure.services.AlteracaoSenhaService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/alterar-senha")
@RestController
@Tag(name = "Alterar Senha", description = "Serviços relacionados a alteração de senha do usuário")
public class AlteracaoSenhaController {

    private final AlteracaoSenhaService alteracaoSenhaService;

    @PostMapping("/cliente")
    public ResponseEntity<Void> alterarSenhaCliente(
            @RequestBody ClienteAlteracaoSenhaDTO alteracaoSenhaDTO) {
        try {
            alteracaoSenhaService.alterarSenha(alteracaoSenhaDTO);
            HttpStatus httpStatus = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/proprietario")
    public ResponseEntity<AlteracaoSenhaDTO> alterarSenhaProprietario(
            @RequestBody ProprietarioAlteracaoSenhaDTO alteracaoSenhaDTO) {
        try {
            alteracaoSenhaService.alterarSenha(alteracaoSenhaDTO);
            HttpStatus httpStatus = HttpStatus.NO_CONTENT;
            return ResponseEntity.status(httpStatus.value()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
