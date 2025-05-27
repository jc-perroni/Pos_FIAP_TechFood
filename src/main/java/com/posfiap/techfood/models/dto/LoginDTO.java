package com.posfiap.techfood.models.dto;

import lombok.Getter;

@Getter
public abstract class LoginDTO {
    private String usuario;
    private String senha;
}
