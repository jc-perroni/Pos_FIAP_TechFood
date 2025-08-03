package com.posfiap.techfood.models;

import com.posfiap.techfood.models.dto.proprietario.ProprietarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario extends Usuario{

    @OneToMany(mappedBy = "proprietario")
    private List<Restaurante> restaurantes;

    public static Proprietario fromDTO(ProprietarioDTO dto) {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome(dto.nome());
        proprietario.setCpf(dto.cpf());
        proprietario.setTelefone(dto.telefone());
        proprietario.setEmail(dto.email());
        proprietario.setUsername(dto.username());
        proprietario.criarConta(dto.password());
        return proprietario;
    }
}
