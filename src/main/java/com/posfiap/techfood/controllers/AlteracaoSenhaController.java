package com.posfiap.techfood.controllers;

import com.posfiap.techfood.models.dto.AlteracaoSenhaDTO;
import com.posfiap.techfood.models.dto.ClienteAlteracaoSenhaDTO;
import com.posfiap.techfood.models.dto.ProprietarioAlteracaoSenhaDTO;
import com.posfiap.techfood.services.AlteracaoSenhaService;
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
public class AlteracaoSenhaController {

    private final AlteracaoSenhaService alteracaoSenhaService;

    @PostMapping("/cliente")
    public ResponseEntity<Void> alterarSenhaCliente(
            @RequestBody ClienteAlteracaoSenhaDTO alteracaoSenhaDTO) {
        alteracaoSenhaService.alterarSenha(alteracaoSenhaDTO);
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(httpStatus.value()).build();
    }

    @PostMapping("/proprietario")
    public ResponseEntity<AlteracaoSenhaDTO> alterarSenhaProprietario(
            @RequestBody ProprietarioAlteracaoSenhaDTO alteracaoSenhaDTO) {
        alteracaoSenhaService.alterarSenha(alteracaoSenhaDTO);
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(httpStatus.value()).build();
    }
}
