package com.posfiap.techfood.models.dto.restaurante;

import com.posfiap.techfood.models.enums.TipoCozinha;

public record RestauranteDTO(String nome, String telefone, Long idProprietario, TipoCozinha tipoCozinha, String horarioFuncionamento) {}
