// src/main/java/com/posfiap/techfood/models/dto/usuario/AlterarPerfilDTO.java
package com.posfiap.techfood.infrastructure.models.dto.usuario;

import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import jakarta.validation.constraints.NotNull;

public record AlterarPerfilDTO(
        @NotNull Long idUsuario,
        @NotNull PerfilUsuario novoPerfil
) {}