package com.posfiap.techfood.controllers;


import com.posfiap.techfood.core.application.controllers.EnderecoController;
import com.posfiap.techfood.core.application.dto.NovoEnderecoDTO;
import com.posfiap.techfood.datasource.EnderecoDataSource;
import com.posfiap.techfood.core.application.dto.EnderecoDTO;
import com.posfiap.techfood.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/enderecos")
@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class EnderecoRestController {

    private final EnderecoService enderecoService;

    @Operation(summary = "Listar todos os endereços paginados")
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAllEnderecos(
            @Parameter(description = "Número da página (começa em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("size") int size) {
        EnderecoDataSource enderecoDataSource = new EnderecoDataSource();
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        return ResponseEntity.ok(
                enderecoController.findAllEnderecos(page, size));
    }

    @Operation(summary = "Buscar endereço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findEnderecoById(
            @Parameter(description = "ID do endereço") @PathVariable("id") Long id) {
        EnderecoDataSource enderecoDataSource = new EnderecoDataSource();
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        return ResponseEntity.ok(enderecoController.findEnderecoById(id));
    }

    @Operation(summary = "Inserir novo endereço")
    @PostMapping
    public ResponseEntity<EnderecoDTO> inserirEndereco(
            @Valid @RequestBody NovoEnderecoDTO endereco) {
        EnderecoDataSource enderecoDataSource = new EnderecoDataSource();
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        return ResponseEntity.ok(enderecoController.inserirEndereco(endereco));
    }

    @Operation(summary = "Atualizar endereço existente")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(
            @Parameter(description = "ID do endereço") @PathVariable("id") Long id,
            @RequestBody NovoEnderecoDTO endereco) {
        EnderecoDataSource enderecoDataSource = new EnderecoDataSource();
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        return ResponseEntity.ok(enderecoController.atualizarEndereco(endereco, id));
    }

    @Operation(summary = "Excluir endereço por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(
            @Parameter(description = "ID do endereço") @PathVariable("id") Long id) {
        EnderecoDataSource enderecoDataSource = new EnderecoDataSource();
        EnderecoController enderecoController = EnderecoController.create(enderecoDataSource);

        enderecoController.excluirEndereco(id);
        return ResponseEntity.ok().build();
    }


}

