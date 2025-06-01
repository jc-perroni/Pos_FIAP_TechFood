package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.InvalidUserNameAccount;
import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.Proprietario;
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

    public void alterarSenha(@NotNull AlteracaoSenhaDTO alteracaoSenhaDTO){
        Usuario usuario = validarUsuario(alteracaoSenhaDTO);
        usuarioService.alterarSenha(alteracaoSenhaDTO.getSenhaAntiga(), alteracaoSenhaDTO.getSenhaNova(), usuario);
        Integer status = salvarSenha(usuario);

        if (status.equals(0)) {
            throw new RuntimeException("Não foi possível alterar a senha.");
        }
    }

    private Usuario validarUsuario(AlteracaoSenhaDTO alteracaoSenhaDTO){
        switch (alteracaoSenhaDTO){
            case ClienteAlteracaoSenhaDTO cliente-> {
                return clienteRepository.findByUsername(cliente.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de cliente inválido."));
            }
            case ProprietarioAlteracaoSenhaDTO proprietario  -> {
                return proprietarioRepository.findByUsername(proprietario.getUsuario()).orElseThrow(() ->
                        new InvalidUserNameAccount("Username de proprietário inválido."));
            }
            default -> throw
                    new IllegalStateException("Não foi possível identificar o tipo de usuário: " + alteracaoSenhaDTO);
        }
    }

    private Integer salvarSenha(Usuario usuario){
        switch (usuario){
            case Cliente cliente-> {
                return clienteRepository.updatePassword(cliente);
            }
            case Proprietario proprietario  -> {
                return proprietarioRepository.updatePassword(proprietario);
            }
            default -> throw
                    new IllegalStateException("Não foi possível identificar o tipo de usuário: " + usuario);
        }
    }
}
