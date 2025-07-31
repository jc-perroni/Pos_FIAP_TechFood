package com.posfiap.techfood.infra.services;

import com.posfiap.techfood.infra.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.infra.models.Cliente;
import com.posfiap.techfood.infra.models.dto.UsuarioEntidadeDTO;
import com.posfiap.techfood.infra.models.dto.ClienteUpdateDTO;
import com.posfiap.techfood.infra.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    public List<Cliente> findAllClientes(int page, int size){
        int offset = (page - 1) * size;
        return clienteRepository.findAll(size, offset);
    }

    public Cliente findClienteById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public void updateCliente(ClienteUpdateDTO cliente, Long id){
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        clienteExistente.updateDataAlteracao();
        clienteExistente.setNome(cliente.nome());
        clienteExistente.setCpf(cliente.cpf());
        clienteExistente.setTelefone(cliente.telefone());
        clienteExistente.setEmail(cliente.email());

        int update = clienteRepository.update(clienteExistente, id);
        if(update ==0) {
            throw new ResourceNotFoundException("O cliente de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertCliente(UsuarioEntidadeDTO cliente){
        Cliente cl = new Cliente(cliente);
        usuarioService.alterarSenha(cliente.password(), cl);
        var insert = clienteRepository.save(cl);
    }

    @Transactional
    public void deleteCliente(Long id) {
        Cliente cl = clienteRepository.findById(id).orElseThrow();
        var delete = clienteRepository.delete(id);
        var deleteUser = clienteRepository.deleteUsuario(cl.getUsername());
        if (delete == 0 || deleteUser == 0){
            throw new ResourceNotFoundException("Cliente para deleção não encontrado com o ID: " + id);
        }
    }

}
