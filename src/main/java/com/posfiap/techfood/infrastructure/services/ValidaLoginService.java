package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.infrastructure.exceptions.InvalidPasswordException;
import com.posfiap.techfood.infrastructure.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.ClienteLoginDTO;
import com.posfiap.techfood.infrastructure.models.dto.LoginDTO;
import com.posfiap.techfood.infrastructure.models.dto.ProprietarioLoginDTO;
import com.posfiap.techfood.infrastructure.models.dto.UsuarioDTO;
import com.posfiap.techfood.infrastructure.repositories.ClienteRepository;
import com.posfiap.techfood.infrastructure.repositories.ProprietarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.posfiap.techfood.infrastructure.services.ValidaSenhaService.autenticarSenha;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidaLoginService {

    private final ClienteRepository clienteRepository;
    private final ProprietarioRepository proprietarioRepository;

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
        switch (loginDTO){
            case ClienteLoginDTO cliente-> {
                log.info("Usuário do tipo Cliente...");
                return clienteRepository.findByUsername(cliente.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de cliente inválido."));
            }
            case ProprietarioLoginDTO proprietario  -> {
                log.info("Usuário do tipo Proprietário...");
                return proprietarioRepository.findByUsername(proprietario.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de proprietário inválido."));
            }
            default -> throw new IllegalStateException("Não foi possível identificar o tipo de usuário: " +
                    loginDTO.getUsuario());
        }
        }
    }

