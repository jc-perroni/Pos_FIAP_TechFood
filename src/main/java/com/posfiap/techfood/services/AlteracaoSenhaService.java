package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.models.Usuario;
import com.posfiap.techfood.models.dto.AlteracaoSenhaDTO;
import com.posfiap.techfood.models.dto.ClienteAlteracaoSenhaDTO;
import com.posfiap.techfood.models.dto.ProprietarioAlteracaoSenhaDTO;
import com.posfiap.techfood.repositories.ClienteRepository;
import com.posfiap.techfood.repositories.ProprietarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlteracaoSenhaService {

    private final ClienteRepository clienteRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final UsuarioService usuarioService;

    public AlteracaoSenhaDTO alterarSenha(@NotNull AlteracaoSenhaDTO alteracaoSenhaDTO){
        var usuario = validarUsuario(alteracaoSenhaDTO);
        usuarioService.alterarSenha(alteracaoSenhaDTO.getSenhaAntiga(), alteracaoSenhaDTO.getSenhaNova(), usuario);
        return alteracaoSenhaDTO;
    }

    private Usuario validarUsuario(AlteracaoSenhaDTO alteracaoSenhaDTO){
        switch (alteracaoSenhaDTO){
            case ClienteAlteracaoSenhaDTO cliente-> {
                log.info("Usuário do tipo Cliente...");
                return clienteRepository.findByUsername(cliente.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de cliente inválido."));
            }
            case ProprietarioAlteracaoSenhaDTO proprietario  -> {
                log.info("Usuário do tipo Proprietário...");
                return proprietarioRepository.findByUsername(proprietario.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de proprietário inválido."));
            }
            default -> throw
                    new IllegalStateException("Não foi possível identificar o tipo de usuário: " + alteracaoSenhaDTO);
        }
    }
}
