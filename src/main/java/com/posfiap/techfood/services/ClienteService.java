package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.models.dto.cliente.ClienteDTO;
import com.posfiap.techfood.models.dto.cliente.ClienteUpdateDTO;
import com.posfiap.techfood.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    public Page<Cliente> findAllClientes(int page, int size){
        return clienteRepository.findAll(PageRequest.of(page, size));
    }

    public Cliente findClienteById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public void updateCliente(ClienteUpdateDTO cliente, Long id){
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        clienteExistente.updateDataAlteracao();
        clienteExistente.setNome(cliente.nome());
        clienteExistente.setCpf(cliente.cpf());
        clienteExistente.setTelefone(cliente.telefone());
        clienteExistente.setEmail(cliente.email());

        clienteRepository.save(clienteExistente);
        log.info("Atualização realizada com sucesso.");

    }

    @Transactional
    public void insertCliente(ClienteDTO cliente){
        Cliente cl = Cliente.fromDTO(cliente);
        usuarioService.alterarSenha(cliente.password(), cl);
        clienteRepository.save(cl);
    }

    @Transactional
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
