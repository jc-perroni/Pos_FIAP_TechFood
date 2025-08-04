package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.InvalidPasswordException;
import com.posfiap.techfood.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.models.Usuario;
import com.posfiap.techfood.models.dto.cliente.ClienteLoginDTO;
import com.posfiap.techfood.models.dto.usuario.LoginDTO;
import com.posfiap.techfood.models.dto.proprietario.ProprietarioLoginDTO;
import com.posfiap.techfood.models.dto.usuario.UsuarioDTO;
import com.posfiap.techfood.repositories.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.posfiap.techfood.services.ValidaSenhaService.autenticarSenha;

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

