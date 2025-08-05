package com.posfiap.techfood.infrastructure.models.dto.restaurante;

import com.posfiap.techfood.infrastructure.models.enums.TipoCozinha;

public record RestauranteDTO(String nome, String telefone, Long idProprietario, TipoCozinha tipoCozinha, String horarioFuncionamento) {}
