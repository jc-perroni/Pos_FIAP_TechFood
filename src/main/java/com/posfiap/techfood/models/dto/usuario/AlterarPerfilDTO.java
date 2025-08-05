// src/main/java/com/posfiap/techfood/models/dto/usuario/AlterarPerfilDTO.java
package com.posfiap.techfood.models.dto.usuario;

import com.posfiap.techfood.models.enums.PerfilUsuario;
import jakarta.validation.constraints.NotNull;

public record AlterarPerfilDTO(
        @NotNull Long idUsuario,
        @NotNull PerfilUsuario novoPerfil
) {}