package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Cliente;
import com.posfiap.techfood.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

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

    public Optional<Cliente> findClienteById(Long id){
        return Optional.ofNullable(clienteRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public void updateCliente(Cliente cliente, Long id){
        var update = clienteRepository.update(cliente, id);
        if(update ==0) {
            throw new RuntimeException("O cliente de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertCliente(Cliente cliente){
        usuarioService.alterarSenha(cliente.getPassword(), cliente);
        var insert = clienteRepository.save(cliente);
        Assert.state(insert ==1, "Erro ao tentar gravar o cliente.");
    }

    public void deleteCliente(Long id) {
        var delete = clienteRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Cliente não encontrado com a ID: " + id);
        }
    }

}
