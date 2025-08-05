package com.posfiap.techfood.infrastructure.models.dto.proprietario;

import com.posfiap.techfood.models.enums.PerfilUsuario;

public record ProprietarioInfoBasicaDTO(String nome, String email, PerfilUsuario perfil)
{}
