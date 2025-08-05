package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidPasswordException;
import com.posfiap.techfood.infrastructure.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.usuario.LoginDTO;
import com.posfiap.techfood.infrastructure.models.dto.usuario.UsuarioDTO;
import com.posfiap.techfood.infrastructure.repositories.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.posfiap.techfood.infrastructure.services.ValidaSenhaService.autenticarSenha;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidaLoginService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO validarLogin(@NotNull LoginDTO loginDTO){

        var usuario = validarUsuario(loginDTO);
        if(autenticarSenha(loginDTO.getSenha(), usuario)) {
            return new UsuarioDTO(usuario.getNome(), usuario.getId());
        }
        else{
            throw new InvalidPasswordException("Senha invalida");
        }
    }

    private Usuario validarUsuario(LoginDTO loginDTO){
        return usuarioRepository.findByUsername(loginDTO.getUsuario()).orElseThrow(() ->
                new InvalidUserNameAccount("Username de cliente inválido."));
            }
    }

