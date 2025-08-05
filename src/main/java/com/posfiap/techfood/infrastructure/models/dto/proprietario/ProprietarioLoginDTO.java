package com.posfiap.techfood.infrastructure.models.dto.proprietario;

import com.posfiap.techfood.infrastructure.models.dto.usuario.LoginDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de login para proprietários de restaurante")
public class ProprietarioLoginDTO extends LoginDTO {
}
