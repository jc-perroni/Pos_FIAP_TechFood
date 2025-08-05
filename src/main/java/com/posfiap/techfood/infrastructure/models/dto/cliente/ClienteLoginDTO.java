package com.posfiap.techfood.infrastructure.models.dto.cliente;

import com.posfiap.techfood.infrastructure.models.dto.usuario.LoginDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de login para clientes")
public class ClienteLoginDTO extends LoginDTO {
}
