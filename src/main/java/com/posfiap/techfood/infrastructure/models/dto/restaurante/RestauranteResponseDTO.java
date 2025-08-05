package com.posfiap.techfood.infrastructure.models.dto.restaurante;

import com.posfiap.techfood.infrastructure.models.Cardapio;
import com.posfiap.techfood.infrastructure.models.Endereco;
import com.posfiap.techfood.infrastructure.models.dto.proprietario.ProprietarioInfoBasicaDTO;
import com.posfiap.techfood.infrastructure.models.enums.TipoCozinha;

import java.util.List;

public record RestauranteResponseDTO(long id, String nome, String telefone, TipoCozinha tipoCozinha, String horarioFuncionamento,
                                     ProprietarioInfoBasicaDTO proprietario, List<Endereco> endereco, Cardapio cardapio) {
}
