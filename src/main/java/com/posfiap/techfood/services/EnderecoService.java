package com.posfiap.techfood.services;

import com.posfiap.techfood.exceptions.ResourceNotFoundException;
import com.posfiap.techfood.models.Endereco;
import com.posfiap.techfood.models.dto.EnderecoDTO;
import com.posfiap.techfood.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final List<Endereco> enderecos = new ArrayList<>();

    public List<Endereco> findAllEnderecos(int page, int size){
        log.info("Acessado o endpoint de retornar todos os endereços");
        int offset = (page - 1) * size;
        return enderecoRepository.findAll(size, offset);
    }

    public Endereco findEnderecoById(Long id){
        log.info("Acessado o endpoint de de encontrar endereço pelo ID");
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public void updateEndereco(Endereco endereco, Long id){
        log.info("Acessado o endpoint de atualização de endereço");
        var update = enderecoRepository.update(endereco, id);
        if(update ==0) {
            throw new ResourceNotFoundException("O endereço de id " + id + " não está cadastrado e não pode ser atualizado");
        }
        log.info("Atualização realizada com sucesso.");
    }

    public void insertEndereco(EnderecoDTO endereco){
        log.info("Acessado o endpoint de salvamento de endereço");
        var insert = enderecoRepository.save(new Endereco(endereco));
    }

    public void deleteEndereco(Long id) {
        log.info("Acessado o endpoint de deleção de endereço");
        var delete = enderecoRepository.delete(id);
        if (delete == 0){
            throw new ResourceNotFoundException("ID do endereço não existe");
        }
    }

    public void addNewEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo.");
        }
        this.enderecos.add(endereco);
    }

    public void removeEndereco(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do endereço não pode ser nula.");
        }
        this.enderecos.removeIf(endereco -> endereco.getId().equals(id));
    }
}
