package com.posfiap.techfood.models.dto;

import com.posfiap.techfood.models.Usuario;
import com.posfiap.techfood.models.enums.TipoUsuario;

public record UsuarioDTO(String nome, Long id, TipoUsuario tipoUsuario) {

}
