package com.posfiap.techfood.models;

public record Endereco (Long id, String rua, String cep, String cidade, String bairro,
                       String complemento, String numero) {}
