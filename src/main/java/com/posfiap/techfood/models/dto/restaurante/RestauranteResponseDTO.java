package com.posfiap.techfood.models.dto.restaurante;

import com.posfiap.techfood.models.Cardapio;
import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioInfoBasicaDTO;
import com.posfiap.techfood.models.enums.TipoCozinha;

import java.util.List;

public record RestauranteResponseDTO(long id, String nome, String telefone, TipoCozinha tipoCozinha, String horarioFuncionamento,
                                     ProprietarioInfoBasicaDTO proprietario, List<Endereco> endereco, Cardapio cardapio) {
}
