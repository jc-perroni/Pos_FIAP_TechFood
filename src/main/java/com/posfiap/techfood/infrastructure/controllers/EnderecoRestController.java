package com.posfiap.techfood.infrastructure.controllers;


import com.posfiap.techfood.core.application.controllers.EnderecoController;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.infrastructure.datasource.EnderecoDataSource;
import com.posfiap.techfood.core.application.dto.EnderecoComIdDTO;
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
public class EnderecoRestController {

    private final EnderecoDataSource enderecoDataSource;

    @Operation(summary = "Listar todos os endereços paginados")
    @GetMapping
    public ResponseEntity<List<EnderecoComIdDTO>> findAllEnderecos(
        @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
        @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size
    ) {
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);
        return ResponseEntity.ok(
                enderecoController.findAllEnderecos(page, size));
    }

    @Operation(summary = "Buscar endereço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoComIdDTO> findEnderecoById(
            @Parameter(description = "ID do endereço") @PathVariable("id") Long id){
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);
        return ResponseEntity.ok(
                enderecoController.findEnderecoById(id));
    }

    @Operation(summary = "Inserir novo endereço")
    @PostMapping
    public ResponseEntity<Void> inserirEndereco(
        @Valid @RequestBody NovoEnderecoDTO novoEnderecoDTO
        ){
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        enderecoController.inserirEndereco(novoEnderecoDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualizar endereço existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEndereco(
        @Parameter(description = "ID do endereço") @PathVariable("id") Long id,
        @RequestBody NovoEnderecoDTO novoEnderecoDTO
    ){
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);
        enderecoController.atualizarEndereco(novoEnderecoDTO, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Excluir endereço por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(
        @Parameter(description = "ID do endereço") @PathVariable("id") Long id
    ){
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);
        enderecoController.excluirEndereco(id);

        return ResponseEntity.ok().build();
    }


}

