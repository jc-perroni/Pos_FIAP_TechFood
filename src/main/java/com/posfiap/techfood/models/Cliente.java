package com.posfiap.techfood.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.posfiap.techfood.models.dto.cliente.ClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "CLIENTES")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    @JsonManagedReference("cliente-endereco")
    private List<Endereco> enderecos = new ArrayList<>();

    @Getter
    private Long id;

    public static Cliente fromDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setTelefone(dto.telefone());
        cliente.setUsername(dto.username());
        cliente.setEmail(dto.email());
        cliente.criarConta(dto.password());
        return cliente;
    }
}