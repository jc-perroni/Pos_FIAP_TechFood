package com.posfiap.techfood.infrastructure.services;

import com.posfiap.techfood.core.domain.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infrastructure.models.Usuario;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteDTO;
import com.posfiap.techfood.infrastructure.models.dto.cliente.ClienteUpdateDTO;
import com.posfiap.techfood.infrastructure.models.enums.PerfilUsuario;
import com.posfiap.techfood.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    private final List<PerfilUsuario> perfilCliente = List.of(PerfilUsuario.CLIENTE, PerfilUsuario.PROPRIETARIO_CLIENTE);

    public Page<Usuario> findAllClientes(int page, int size){
        return usuarioRepository.findAllByPerfilIn(perfilCliente, PageRequest.of(page, size));
    }

    public Usuario findClienteById(Long id){
        return retornarCliente(id);
    }

    @Transactional
    public void updateCliente(ClienteUpdateDTO cliente, Long id){
        Usuario clienteExistente = retornarCliente(id);
        clienteExistente.updateDataAlteracao();
        clienteExistente.setNome(cliente.nome());
        clienteExistente.setCpf(cliente.cpf());
        clienteExistente.setTelefone(cliente.telefone());
        clienteExistente.setEmail(cliente.email());

        usuarioRepository.save(clienteExistente);
        log.info("Atualização realizada com sucesso.");

    }

    @Transactional
    public void insertCliente(ClienteDTO cliente){
        Usuario novoUsuario = new Usuario();
        novoUsuario.setPerfil(PerfilUsuario.CLIENTE);
        novoUsuario.setNome(cliente.nome());
        novoUsuario.setCpf(cliente.cpf());
        novoUsuario.setTelefone(cliente.telefone());
        novoUsuario.setEmail(cliente.email());
        novoUsuario.setUsername(cliente.username());
        usuarioService.alterarSenha(cliente.password(), novoUsuario);
        usuarioRepository.save(novoUsuario);
    }

    @Transactional
    public void deleteCliente(Long id) {
        log.info("Acessado o endpoint de deleção de cliente");
        Usuario usuario = retornarCliente(id);
        if(usuario.getPerfil().equals(PerfilUsuario.CLIENTE)) {
            usuarioRepository.deleteById(usuario.getId());
        }
        else usuarioService.alterarPerfil(id, PerfilUsuario.PROPRIETARIO);
    }

    private Usuario retornarCliente(Long id){
        return usuarioRepository.findByIdAndPerfilIn(id, perfilCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}

