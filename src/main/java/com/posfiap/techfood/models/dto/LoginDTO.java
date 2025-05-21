package com.posfiap.techfood.models.dto;

import com.posfiap.techfood.models.enums.TipoUsuario;

public record LoginDTO (String usuario, String senha, TipoUsuario tipoUsuario){
}
