package com.posfiap.techfood.models;

public record Endereco (Long id, Long idCliente, String rua, String cep, String cidade, String bairro,
                       String complemento, String numero) {}
