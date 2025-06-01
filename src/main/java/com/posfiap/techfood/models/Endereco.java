package com.posfiap.techfood.models;

import com.posfiap.techfood.models.enums.TipoEndereco;

public record Endereco (Long id, String idEntidade, TipoEndereco tipoEndereco, String rua,
                        String cep, String cidade, String bairro, String complemento, String numero) {}
