package com.posfiap.techfood.infrastructure.controllers;


import com.posfiap.techfood.infrastructure.models.Endereco;
import com.posfiap.techfood.infrastructure.models.dto.EnderecoDTO;
import com.posfiap.techfood.infrastructure.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/enderecos")
@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Operation(summary = "Listar todos os endereços paginados")
    @GetMapping
    public ResponseEntity<List<Endereco>> findAllEnderecos(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
        @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size
    ) {
        var enderecos = enderecoService.findAllEnderecos(page, size);
        return ResponseEntity.ok(enderecos);
    }

    @Operation(summary = "Buscar endereço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findEnderecoById(
            @Parameter(description = "ID do endereço") @PathVariable("id") Long id){
        var endereco = enderecoService.findEnderecoById(id);
        return ResponseEntity.ok(endereco);
    }

    @Operation(summary = "Inserir novo endereço")
    @PostMapping
    public ResponseEntity<Void> inserirEndereco(
        @Valid @RequestBody EnderecoDTO endereco
        ){
        enderecoService.insertEndereco(endereco);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar endereço existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEndereco(
        @Parameter(description = "ID do endereço") @PathVariable("id") Long id,
        @RequestBody Endereco endereco
    ){
        enderecoService.updateEndereco(endereco, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir endereço por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(
        @Parameter(description = "ID do endereço") @PathVariable("id") Long id
    ){
        enderecoService.deleteEndereco(id);
        return ResponseEntity.ok().build();
    }


}

