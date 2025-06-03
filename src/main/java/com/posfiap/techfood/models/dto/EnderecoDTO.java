package com.posfiap.techfood.models.dto;

import com.posfiap.techfood.models.enums.TipoEndereco;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull(message = "O ID para criar o endereço não pode ser nulo")
        Long idEntidade,

        @NotNull(message = "O tipo de endereço não pode ser nulo: 'CLIENTE' ou 'RESTAURANTE'")
        TipoEndereco tipoEndereco,

        @NotNull(message = "Nome da rua necessário")
        String rua,

        @NotNull(message = "CEP necessário")
        String cep,

        @NotNull(message = "Nome da cidade necessário")
        String cidade,

        @NotNull(message = "Nome do bairro necessário")
        String bairro,

        String complemento,

        @NotNull(message = "Número necessário")
        String numero
) {
}
